package edu.project3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.HelpFunctions.getStatusCodeName;
import static edu.project3.Main.URL_REGEX;
import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.isRegularFile;

@SuppressWarnings({"HideUtilityClassConstructor", "RegexpSinglelineJava",
    "MagicNumber", "MultipleStringLiterals"})
public class DefaultGenerator {

    public static String createDefaultReport(LogReport logReport) {
        StringBuilder sb = new StringBuilder();
        String table1 = generateDefaultTable1(logReport);
        String table2 = generateDefaultTable2(logReport);
        String table3 = generateDefaultTable3(logReport);

        sb.append(table1)
            .append(table2)
            .append(table3);

        return sb.toString();
    }

    private static String generateDefaultTable1(LogReport logReport) {

        String strPath = logReport.pathArg;
        return (
                // Заголовок
                "### Общая информация ###\n\n"

                // Шапка
                + "        Метрика        |  Значение\n"
                + "-----------------------+-----------\n"
                + " Файл(-ы) или URL      | "

                // Вставляем название файла(-ов) или URL
                + getURLOrFilesNames(strPath)

                // Остаток таблицы
                + " Начальная дата        | " + logReport.getFromDate() + "\n"
                + " Конечная дата         | " + logReport.getToDate() + "\n"
                + " Количество запросов   | " + logReport.getTotalRequests() + "\n"
                + " Средний размер ответа | " + logReport.getAverageResponseSize() + "b\n\n");
    }

    private static String getURLOrFilesNames(String strPath) {

        Matcher matcher = Pattern.compile(URL_REGEX).matcher(strPath);
        // Если парсили из URL
        if (matcher.find()) {
            return strPath + "\n";
        }

        Path path = Path.of(strPath);
        // Если парсили из файла
        if (isRegularFile(path)) {
            return path.getFileName() + "\n";
        }

        // Если парсили из директории
        if (isDirectory(path)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                StringBuilder sb = new StringBuilder();
                boolean firstPrint = true;

                for (Path filePath : directoryStream) {
                    if (!isRegularFile(filePath)) {
                        continue;
                    }

                    if (firstPrint) {
                        sb.append(filePath.getFileName())
                            .append("\n");
                        firstPrint = false;
                        continue;
                    }
                    sb.append(" ".repeat(23))
                        .append("| ")
                        .append(filePath.getFileName())
                        .append("\n");
                }
                return sb.toString();
            } catch (IOException e) {
                System.out.println("Output error!");
            }
        }

        return "";
    }

    private static String generateDefaultTable2(LogReport logReport) {

        StringBuilder sb = new StringBuilder();

        // Заголовок
        sb.append("### Запрашиваемые ресурсы ###\n\n");

        // Находим самый длинный ресурс
        int maxLength = 0;
        for (Map.Entry<String, Integer> entry : logReport.getResources().entrySet()) {
            if (entry.getKey().length() > maxLength) {
                maxLength = entry.getKey().length();
            }
        }

        if (maxLength == 0) {
            return "";
        }

        // Шапка
        int leftSpaces = maxLength / 2 - 2;
        int rightSpaces = maxLength - leftSpaces - 4;
        sb.append(" ".repeat(leftSpaces)) // для вывода слова "Ресурс" по центру
            .append("Ресурс")
            .append(" ".repeat(rightSpaces)) // для вывода слова "Ресурс" по центру
            .append("| Количество\n");
        sb.append("-".repeat(maxLength + 2)).append("+------------\n");

        // Тело таблицы
        for (Map.Entry<String, Integer> entry : logReport.getResources().entrySet()) {
            sb.append(" ".repeat(maxLength - entry.getKey().length() + 1))
                .append(entry.getKey())
                .append(" |    ")
                .append(entry.getValue())
                .append("\n");
        }
        return sb.toString();

    }

    private static String generateDefaultTable3(LogReport logReport) {

        StringBuilder sb = new StringBuilder();

        // Заголовок
        sb.append("\n### Коды ответа ###\n\n");

        // Находим самое длинное описание кода ответа
        int maxLength = 0;
        for (Map.Entry<Integer, Integer> entry : logReport.getStatusCodes().entrySet()) {
            int statusCodeLength = getStatusCodeName(entry.getKey()).length();
            if (statusCodeLength > maxLength) {
                maxLength = statusCodeLength;
            }
        }

        if (maxLength == 0) {
            return "";
        }

        // Шапка
        int leftSpaces = maxLength / 2 - 3;
        int rightSpaces = maxLength - leftSpaces - 6;
        sb.append(" Код |")
            .append(" ".repeat(leftSpaces)) // для вывода слова "Описание" по центру
            .append("Описание")
            .append(" ".repeat(rightSpaces)) // для вывода слова "Описание" по центру
            .append("| Количество\n");

        sb.append("-----+")
            .append("-".repeat(maxLength + 2))
            .append("+------------\n");

        // Тело таблицы
        for (Map.Entry<Integer, Integer> entry : logReport.getStatusCodes().entrySet()) {
            sb.append(" ")
                .append(entry.getKey())
                .append(" | ")
                .append(getStatusCodeName(entry.getKey()))
                .append(" ".repeat(maxLength - getStatusCodeName(entry.getKey()).length()))
                .append(" |    ")
                .append(entry.getValue())
                .append("\n");
        }

        return sb.toString();
    }
}
