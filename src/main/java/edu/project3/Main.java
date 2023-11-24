package edu.project3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import static edu.project3.AsciiDocGenerator.createAdocReport;
import static edu.project3.DefaultGenerator.createDefaultReport;
import static edu.project3.HelpFunctions.generateLogReport;
import static edu.project3.HelpFunctions.isWithinRange;
import static edu.project3.MarkdownGenerator.createMarkdownReport;
import static edu.project3.Parsers.parseCommandLine;
import static edu.project3.Parsers.parsePath;

@SuppressWarnings({"HideUtilityClassConstructor", "RegexpSinglelineJava", "LineLength"})
public class Main {

    public static final String LINE_REGEX = "^(.+) - (.+) \\[(.+)\\] \"(.+)\" (\\d+) (\\d+) \"(.+)\" \"(.+)\"";

    public static final String URL_REGEX =
        "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)([).!';/?:,]\\[:blank:\\])?$";

    public static final int DATE_GROUP = 3;

    public static final int REQUEST_URI_GROUP = 4;

    public static final int STATUS_CODE_GROUP = 5;

    public static final int RESPONSE_SIZE_GROUP = 6;

    public static final Map<String, String> ARGUMENTS = new HashMap<>();

    public static void main(String[] args) {

        parseCommandLine(args);
        String pathArg = ARGUMENTS.get("path");
        String fromArg = ARGUMENTS.get("from");
        String toArg = ARGUMENTS.get("to");
        String formatArg = ARGUMENTS.get("format");

        Stream<LogRecord> logRecordsStream = parsePath(pathArg)
            .filter(logRecord -> isWithinRange(logRecord, fromArg, toArg));

        LogReport logReport = generateLogReport(logRecordsStream, pathArg, fromArg, toArg);

        switch (formatArg) {
            case "markdown" -> createMarkdownReport(logReport);
            case "adoc" -> createAdocReport(logReport);
            case null, default -> System.out.println("\n" + createDefaultReport(logReport));
        }
    }
}
