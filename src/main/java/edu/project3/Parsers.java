package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import static edu.project3.Main.ARGUMENTS;
import static edu.project3.Main.DATE_GROUP;
import static edu.project3.Main.LINE_REGEX;
import static edu.project3.Main.REQUEST_URI_GROUP;
import static edu.project3.Main.RESPONSE_SIZE_GROUP;
import static edu.project3.Main.STATUS_CODE_GROUP;
import static edu.project3.Main.URL_REGEX;
import static java.time.temporal.ChronoUnit.SECONDS;

@SuppressWarnings({"HideUtilityClassConstructor", "RegexpSinglelineJava"})
public class Parsers {

    private static final int MAX_DELAY = 10;

    public static void parseCommandLine(String[] args) {

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
            System.out.println("\nCommand line parsing error!\n");
        }
    }

    public static Stream<LogRecord> parsePath(String path) {
        // Проверка на URL
        Matcher matcher = Pattern.compile(URL_REGEX).matcher(path);
        if (matcher.find()) {
            return parseURL(path);
        }

        Path pathArg = Path.of(path);
        // Проверка на директорию
        if (Files.isDirectory(pathArg)) {
            return parseLogFiles(path);
        }
        // Проверка на файл
        if (Files.isRegularFile(pathArg)) {
            return parseLogFile(path);
        }

        return Stream.empty();
    }

    private static Stream<LogRecord> parseURL(String url) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .timeout(Duration.of(MAX_DELAY, SECONDS))
            .GET()
            .build();

        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            List<LogRecord> logRecords = new ArrayList<>();
            for (String line : response.body().split("\n")) {
                logRecords.add(parseLogRecord(line));
            }

            List<Stream<LogRecord>> logRecordsStream = new ArrayList<>();
            logRecordsStream.add(logRecords.stream());

            return logRecordsStream.stream()
                .reduce(Stream::concat)
                .orElseGet(Stream::empty);
        } catch (InterruptedException | IOException e) {
            System.out.println("\nParsing from URL error!\n");
        }

        return Stream.empty();
    }

    private static Stream<LogRecord> parseLogFiles(String pathToDir) {

        Path pathArg = Path.of(pathToDir);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(pathArg)) {
            List<Stream<LogRecord>> logRecordsStreams = new ArrayList<>();

            for (Path filePath : directoryStream) {
                if (Files.isRegularFile(filePath)) {
                    logRecordsStreams.add(parseSingleLogFile(filePath));
                }
            }
            return logRecordsStreams.stream()
                .reduce(Stream::concat)
                .orElseGet(Stream::empty);
        } catch (IOException e) {
            System.out.println("\nLog files parsing error!\n");
        }

        return Stream.empty();
    }

    private static Stream<LogRecord> parseLogFile(String pathToFile) {
        Path pathArg = Path.of(pathToFile);
        try {
            List<Stream<LogRecord>> logRecordsStream = new ArrayList<>();
            logRecordsStream.add(parseSingleLogFile(pathArg));

            return logRecordsStream.stream()
                .reduce(Stream::concat)
                .orElseGet(Stream::empty);
        } catch (IOException e) {
            System.out.println("\nLog file parsing error!\n");
        }
        return Stream.empty();
    }

    private static Stream<LogRecord> parseSingleLogFile(Path filePath) throws IOException {
        BufferedReader br = Files.newBufferedReader(filePath);
        return br.lines().map(Parsers::parseLogRecord);
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
}
