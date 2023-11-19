package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

@SuppressWarnings({"HideUtilityClassConstructor", "RegexpSinglelineJava",
    "MultipleStringLiterals", "MagicNumber", "InnerTypeLast", "UncommentedMain"})
public class NginxLogStats {

    private static final String LINE_REGEX = "^(.+) - (.+) \\[(.+)\\] \"(.+)\" (\\d+) (\\d+) \"(.+)\" \"(.+)\"";

    private static final int DATE_GROUP = 3;

    private static final int REQUEST_URI_GROUP = 4;

    private static final int STATUS_CODE_GROUP = 5;

    private static final int RESPONSE_SIZE_GROUP = 6;

    private static final Map<String, String> ARGUMENTS = new HashMap<>();

    public static void main(String[] args) {

        parseCommandLine(args);
        String pathArg = ARGUMENTS.get("path");
        String fromArg = ARGUMENTS.get("from");
        String toArg = ARGUMENTS.get("to");
        String formatArg = ARGUMENTS.get("format");

        Stream<LogRecord> logRecordsStream = Objects.requireNonNull(parseLogFiles(pathArg))
            .filter(logRecord -> isWithinRange(logRecord, fromArg, toArg));

        LogReport logReport = generateLogReport(logRecordsStream);

        String output = switch (formatArg) {
            case "markdown" -> generateMarkdownReport(logReport);
            case "adoc" -> generateAdocReport(logReport);
            default -> generateDefaultReport(logReport);
        };
        System.out.println(output);
    }

    private static void parseCommandLine(String[] args) {

        Options options = new Options();

        options.addOption(Option.builder()
            .longOpt("path")
            .desc("Path to .log file or directory with .log files")
            .build());

        options.addOption(Option.builder()
            .longOpt("from")
            .desc("Time interval start")
            .build());

        options.addOption(Option.builder()
            .longOpt("to")
            .desc("Time interval end")
            .build());

        options.addOption(Option.builder()
            .longOpt("format")
            .desc("Format of output (markdown or adoc)")
            .build());

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            for (int i = 0; i < line.getOptions().length; i++) {
                ARGUMENTS.put(line.getOptions()[i].getLongOpt(), line.getArgs()[i]);
            }
        } catch (ParseException e) {
            System.out.println("Command line parsing error!");
        }
    }

    private static Stream<LogRecord> parseLogFiles(String path) {

        // TODO: ДОБАВИТЬ ПОДДЕРЖКУ URL

        Path pathArg = Path.of(path);

        if (Files.isRegularFile(pathArg)) {
            try {
                List<Stream<LogRecord>> logRecordsStreams = new ArrayList<>();
                logRecordsStreams.add(parseLogFile(pathArg));

                return logRecordsStreams.stream()
                    .reduce(Stream::concat)
                    .orElseGet(Stream::empty);
            } catch (IOException e) {
                System.out.println("Log files parsing error!");
            }
        }

        if (Files.isDirectory(pathArg)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(pathArg)) {
                List<Stream<LogRecord>> logRecordsStreams = new ArrayList<>();

                for (Path filePath : directoryStream) {
                    if (Files.isRegularFile(filePath)) {
                        logRecordsStreams.add(parseLogFile(filePath));
                    }
                }
                return logRecordsStreams.stream()
                    .reduce(Stream::concat)
                    .orElseGet(Stream::empty);

            } catch (IOException e) {
                System.out.println("Log files parsing error!");
            }
        }

        // TODO: URL

        return null;
    }

    private static Stream<LogRecord> parseLogFile(Path filePath) throws IOException {
        BufferedReader br = Files.newBufferedReader(filePath);
        return br.lines().map(NginxLogStats::parseLogRecord);
    }

    private static LogRecord parseLogRecord(String logLine) {

        Matcher matcher = Pattern.compile(LINE_REGEX).matcher(logLine);

        OffsetDateTime dateTime = null;
        int statusCode = 0;
        int responseSize = 0;
        String requestUri = null;

        while (matcher.find()) {
            dateTime = OffsetDateTime.parse(
                matcher.group(DATE_GROUP),
                DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.US)
            );
            statusCode = Integer.parseInt(matcher.group(STATUS_CODE_GROUP));
            responseSize = Integer.parseInt(matcher.group(RESPONSE_SIZE_GROUP));
            requestUri = matcher.group(REQUEST_URI_GROUP);
        }

        assert dateTime != null;
        return new LogRecord(dateTime.toLocalDateTime(), statusCode, responseSize, requestUri);
    }

    private static boolean isWithinRange(LogRecord logRecord, String fromArg, String toArg) {
        LocalDate from = (fromArg != null) ? LocalDate.parse(fromArg, DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
        LocalDate to = (toArg != null) ? LocalDate.parse(toArg, DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;

        return (from == null || logRecord.dateTime().isAfter(from.atStartOfDay())
            || logRecord.dateTime().isEqual(from.atStartOfDay()))
            && (to == null || logRecord.dateTime().isBefore(to.atStartOfDay())
            || logRecord.dateTime().isEqual(to.atStartOfDay()));
    }

    private static LogReport generateLogReport(Stream<LogRecord> logRecordsStream) {
        LogReport logReport = new LogReport();
        logRecordsStream.forEach(logRecord -> {
            logReport.incrementTotalRequests();
            logReport.addResource(logRecord.requestUri());
            logReport.addStatusCode(logRecord.statusCode());
            logReport.addResponseSize(logRecord.responseSize());
        });
        return logReport;
    }

    private static String getStatusCodeName(int statusCode) {

        // TODO: ДОБАВИТЬ ЕЩЕ КОДЫ

        return switch (statusCode) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "Unknown";
        };
    }

    private record LogRecord(LocalDateTime dateTime, int statusCode, int responseSize, String requestUri) { }

    // TODO: ПОПРАВИТЬ ВЫВОД
    private static String generateDefaultReport(LogReport logReport) {
        StringBuilder sb = new StringBuilder();
        sb.append("#### Общая информация\n\n");
        sb.append("|        Метрика        |     Значение |\n");
        sb.append("|:---------------------:|-------------:|\n");
        sb.append("|       Файл(-ы)        | `access.log` |\n");
        sb.append("|    Начальная дата     |   ").append(logReport.getFromDate()).append(" |\n");
        sb.append("|     Конечная дата     |   ").append(logReport.getToDate()).append(" |\n");
        sb.append("|  Количество запросов  |   ").append(logReport.getTotalRequests()).append(" |\n");
        sb.append("| Средний размер ответа |   ").append(logReport.getAverageResponseSize()).append("b |\n\n");
        sb.append("#### Запрашиваемые ресурсы\n\n");
        sb.append("|     Ресурс      | Количество |\n");
        sb.append("|:---------------:|-----------:|\n");
        for (Map.Entry<String, Integer> entry : logReport.getResources().entrySet()) {
            sb.append("|  ").append(entry.getKey()).append("  |   ").append(entry.getValue()).append(" |\n");
        }
        sb.append("\n#### Коды ответа\n\n");
        sb.append("| Код |          Имя          | Количество |\n");
        sb.append("|:---:|:---------------------:|-----------:|\n");
        for (Map.Entry<Integer, Integer> entry : logReport.getStatusCodes().entrySet()) {
            sb.append("|  ").append(entry.getKey()).append("  |   ")
                .append(getStatusCodeName(entry.getKey())).append("   |   ")
                .append(entry.getValue()).append(" |\n");
        }
        return sb.toString();
    }

    private static String generateMarkdownReport(LogReport logReport) {
        StringBuilder sb = new StringBuilder();
        sb.append("#### Общая информация\n\n");
        sb.append("|        Метрика        |     Значение |\n");
        sb.append("|:---------------------:|-------------:|\n");
        sb.append("|       Файл(-ы)        | `access.log` |\n");
        sb.append("|    Начальная дата     |   ").append(logReport.getFromDate()).append(" |\n");
        sb.append("|     Конечная дата     |   ").append(logReport.getToDate()).append(" |\n");
        sb.append("|  Количество запросов  |   ").append(logReport.getTotalRequests()).append(" |\n");
        sb.append("| Средний размер ответа |   ").append(logReport.getAverageResponseSize()).append("b |\n\n");
        sb.append("#### Запрашиваемые ресурсы\n\n");
        sb.append("|     Ресурс      | Количество |\n");
        sb.append("|:---------------:|-----------:|\n");
        for (Map.Entry<String, Integer> entry : logReport.getResources().entrySet()) {
            sb.append("|  ").append(entry.getKey()).append("  |   ").append(entry.getValue()).append(" |\n");
        }
        sb.append("\n#### Коды ответа\n\n");
        sb.append("| Код |          Имя          | Количество |\n");
        sb.append("|:---:|:---------------------:|-----------:|\n");
        for (Map.Entry<Integer, Integer> entry : logReport.getStatusCodes().entrySet()) {
            sb.append("|  ").append(entry.getKey()).append("  |   ")
                .append(getStatusCodeName(entry.getKey())).append("   |   ")
                .append(entry.getValue()).append(" |\n");
        }
        return sb.toString();
    }

    private static String generateAdocReport(LogReport logReport) {
        StringBuilder sb = new StringBuilder();
        sb.append("==== Общая информация ====\n\n");
        sb.append("|===\n");
        sb.append("|    Метрика   | Значение\n");
        sb.append("|       Файл(-ы)   | `access.log`\n");
        sb.append("| Начальная дата   | ").append(logReport.getFromDate()).append("\n");
        sb.append("| Конечная дата   | ").append(logReport.getToDate()).append("\n");
        sb.append("| Количество запросов | ").append(logReport.getTotalRequests()).append("\n");
        sb.append("| Средний размер ответа | ").append(logReport.getAverageResponseSize()).append("b\n");
        sb.append("|===\n\n");
        sb.append("==== Запрашиваемые ресурсы ====\n\n");
        sb.append("|===\n");
        sb.append("|    Ресурс     | Количество\n");
        for (Map.Entry<String, Integer> entry : logReport.getResources().entrySet()) {
            sb.append("|  ").append(entry.getKey()).append("  |   ").append(entry.getValue()).append("\n");
        }
        sb.append("|===\n\n");
        sb.append("==== Коды ответа ====\n\n");
        sb.append("|===\n");
        sb.append("| Код |          Имя         | Количество\n");
        for (Map.Entry<Integer, Integer> entry : logReport.getStatusCodes().entrySet()) {
            sb.append("|  ").append(entry.getKey()).append("  |   ")
                .append(getStatusCodeName(entry.getKey())).append("   |   ")
                .append(entry.getValue()).append("\n");
        }
        sb.append("|===\n");
        return sb.toString();
    }

    private static class LogReport {

        private final List<String> resources = new ArrayList<>();

        private final Map<Integer, Integer> statusCodes = new HashMap<>();

        private final List<Integer> responseSizes = new ArrayList<>();

        private int totalRequests = 0;

        private OffsetDateTime fromDate;

        private OffsetDateTime toDate;

        private void addResource(String resource) {
            resources.add(resource);
        }

        private void addStatusCode(int statusCode) {
            statusCodes.put(statusCode, statusCodes.getOrDefault(statusCode, 0) + 1);
        }

        private void addResponseSize(int responseSize) {
            responseSizes.add(responseSize);
        }

        private void incrementTotalRequests() {
            totalRequests++;
        }

        private String getFromDate() {
            if (fromDate != null) {
                return fromDate.toString();
            }
            return "-";
        }

        private String getToDate() {
            if (toDate != null) {
                return toDate.toString();
            }
            return "-";
        }

        private int getTotalRequests() {
            return totalRequests;
        }

        private String getAverageResponseSize() {
            if (responseSizes.isEmpty()) {
                return "0";
            }
            int sum = responseSizes.stream().mapToInt(Integer::intValue).sum();
            return String.valueOf(sum / responseSizes.size());
        }

        private Map<String, Integer> getResources() {
            Map<String, Integer> resourceCounts = new HashMap<>();
            for (String resource : resources) {
                resourceCounts.put(resource, resourceCounts.getOrDefault(resource, 0) + 1);
            }
            return resourceCounts;
        }

        private Map<Integer, Integer> getStatusCodes() {
            return statusCodes;
        }
    }
}
