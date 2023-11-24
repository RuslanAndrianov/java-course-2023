package edu.project3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.HelpFunctions.getStatusCodeName;
import static edu.project3.Main.URL_REGEX;
import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.isRegularFile;

@SuppressWarnings({"HideUtilityClassConstructor", "RegexpSinglelineJava", "MultipleStringLiterals"})
public class AsciiDocGenerator {

    private static final String TABLE_OPTIONS = "[options=header,align=center]\n";

    private static final String TABLE_BOUNDARY = "|=======================\n";

    public static void createAdocReport(LogReport logReport) {

        Path mdFilePath = Path.of(System.getProperty("user.dir") + "\\nginx-log-stats\\report.adoc");
        StringBuilder reportBody = new StringBuilder();
        try {
            deleteIfExists(mdFilePath);
            reportBody.append(fillAsciiDocReport(logReport));
            BufferedWriter writer = new BufferedWriter(new FileWriter(mdFilePath.toString()));
            writer.write(reportBody.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("\nAsciiDoc report creation error!\n");
        }
    }

    private static String fillAsciiDocReport(LogReport logReport) {

        StringBuilder sb = new StringBuilder();
        String table1 = generateAsciiDocTable1(logReport);
        String table2 = generateAsciiDocTable2(logReport);
        String table3 = generateAsciiDocTable3(logReport);

        sb.append(table1)
            .append(table2)
            .append(table3);

        return sb.toString();
    }

    private static String generateAsciiDocTable1(LogReport logReport) {

        String strPath = logReport.pathArg;
        return (
            // Заголовок
            "### Общая информация ###\n\n"

            // Шапка
            + TABLE_OPTIONS
            + TABLE_BOUNDARY
            + "| Метрика | Значение\n"
            + "| Файл(-ы) или URL | "

            // Вставляем название файла(-ов) или URL
            + getURLOrFilesNames(strPath)

            // Остаток таблицы
            + "| Начальная дата | " + logReport.getFromDate() + "\n"
            + "| Конечная дата | " + logReport.getToDate() + "\n"
            + "| Количество запросов | " + logReport.getTotalRequests() + "\n"
            + "| Средний размер ответа | " + logReport.getAverageResponseSize() + "b\n\n"

            // Конец таблицы
            + TABLE_BOUNDARY);
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
                    sb.append("| | ")
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

    private static String generateAsciiDocTable2(LogReport logReport) {

        StringBuilder sb = new StringBuilder();

        // Заголовок
        sb.append("### Запрашиваемые ресурсы ###\n\n");

        // Шапка
        sb.append(TABLE_OPTIONS)
            .append(TABLE_BOUNDARY)
            .append("| Ресурс | Количество\n");

        // Тело таблицы
        for (Map.Entry<String, Integer> entry : logReport.getResources().entrySet()) {
            sb.append("| ")
                .append(entry.getKey())
                .append(" | ")
                .append(entry.getValue())
                .append("\n");
        }

        // Конец таблицы
        sb.append(TABLE_BOUNDARY);

        return sb.toString();
    }

    private static String generateAsciiDocTable3(LogReport logReport) {

        StringBuilder sb = new StringBuilder();

        // Заголовок
        sb.append("\n### Коды ответа ###\n\n");

        // Шапка
        sb.append(TABLE_OPTIONS)
            .append(TABLE_BOUNDARY)
            .append("| Код | Описание | Количество\n");

        // Тело таблицы
        for (Map.Entry<Integer, Integer> entry : logReport.getStatusCodes().entrySet()) {
            sb.append("| ")
                .append(entry.getKey())
                .append(" | ")
                .append(getStatusCodeName(entry.getKey()))
                .append(" | ")
                .append(entry.getValue())
                .append("\n");
        }

        // Конец таблицы
        sb.append(TABLE_BOUNDARY);

        return sb.toString();
    }
}
