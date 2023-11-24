package edu.project3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.HelpFunctions.getStatusCodeName;
import static edu.project3.Main.URL_REGEX;
import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.isRegularFile;

@SuppressWarnings({"HideUtilityClassConstructor", "RegexpSinglelineJava", "MultipleStringLiterals"})
public class MarkdownGenerator {

    public static void createMarkdownReport(LogReport logReport) {

        Path mdFilePath = Path.of(System.getProperty("user.dir") + "\\nginx-log-stats\\report.md");
        StringBuilder reportBody = new StringBuilder();
        try {
            deleteIfExists(mdFilePath);
            reportBody.append(fillMarkdownReport(logReport));
            BufferedWriter writer = new BufferedWriter(new FileWriter(mdFilePath.toString()));
            writer.write(reportBody.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("\nMarkdown report creation error!\n");
        }
    }

    private static String fillMarkdownReport(LogReport logReport) {

        StringBuilder sb = new StringBuilder();
        String table1 = generateMarkdownTable1(logReport);
        String table2 = generateMarkdownTable2(logReport);
        String table3 = generateMarkdownTable3(logReport);

        sb.append(table1)
            .append(table2)
            .append(table3);

        return sb.toString();
    }

    private static String generateMarkdownTable1(LogReport logReport) {

        String strPath = logReport.pathArg;
        return (
            // Заголовок
            "### Общая информация ###\n\n"

            // Шапка
            + "| Метрика | Значение |\n"
            + "|:---:|:---:|\n"
            + "| Файл(-ы) или URL | "

            // Вставляем название файла(-ов) или URL
            + getURLOrFilesNames(strPath)

            // Остаток таблицы
            + "| Начальная дата | " + logReport.getFromDate() + " |\n"
            + "| Конечная дата | " + logReport.getToDate() + " |\n"
            + "| Количество запросов | " + logReport.getTotalRequests() + " |\n"
            + "| Средний размер ответа | " + logReport.getAverageResponseSize() + "b |\n\n");
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
            return path.getFileName() + " |\n";
        }

        // Если парсили из директории
        if (isDirectory(path)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                StringBuilder sb = new StringBuilder();
                boolean firstPrint = true;
                List<Path> sortedPaths = new ArrayList<>();

                for (Path filePath : directoryStream) {
                    sortedPaths.add(filePath);
                }

                sortedPaths = sortedPaths.stream()
                    .sorted(Comparator.comparing(filePath -> filePath.getFileName().toString()))
                    .toList();

                for (Path filePath : sortedPaths) {
                    if (!isRegularFile(filePath)) {
                        continue;
                    }
                    if (firstPrint) {
                        sb.append(filePath.getFileName())
                            .append(" |\n");
                        firstPrint = false;
                        continue;
                    }
                    sb.append("| | ")
                        .append(filePath.getFileName())
                        .append(" |\n");
                }
                return sb.toString();
            } catch (IOException e) {
                System.out.println("Output error!");
            }
        }

        return "";
    }

    private static String generateMarkdownTable2(LogReport logReport) {

        StringBuilder sb = new StringBuilder();

        // Заголовок
        sb.append("### Запрашиваемые ресурсы ###\n\n");

        // Шапка
        sb.append("| Ресурс | Количество |\n")
            .append("|:---:|:---:|\n");

        // Тело таблицы
        for (Map.Entry<String, Integer> entry : logReport.getResources().entrySet()) {
            sb.append("| ")
                .append(entry.getKey())
                .append(" | ")
                .append(entry.getValue())
                .append(" |\n");
        }

        return sb.toString();
    }

    private static String generateMarkdownTable3(LogReport logReport) {

        StringBuilder sb = new StringBuilder();

        // Заголовок
        sb.append("\n### Коды ответа ###\n\n");

        // Шапка
        sb.append("| Код | Описание | Количество |\n")
            .append("|:---:|:---:|:---:|\n");

        // Тело таблицы
        for (Map.Entry<Integer, Integer> entry : logReport.getStatusCodes().entrySet()) {
            sb.append("| ")
                .append(entry.getKey())
                .append(" | ")
                .append(getStatusCodeName(entry.getKey()))
                .append(" | ")
                .append(entry.getValue())
                .append(" |\n");
        }

        return sb.toString();
    }
}
