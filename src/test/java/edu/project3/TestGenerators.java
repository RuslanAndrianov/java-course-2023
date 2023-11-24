package edu.project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project3.AsciiDocGenerator.createAdocReport;
import static edu.project3.DefaultGenerator.createDefaultReport;
import static edu.project3.HelpFunctions.generateLogReport;
import static edu.project3.HelpFunctions.isWithinRange;
import static edu.project3.MarkdownGenerator.createMarkdownReport;
import static edu.project3.Parsers.parsePath;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGenerators {

    @Test
    @DisplayName("Тест default вывода")
    void testDefaultReport() {

        String pathArg = "nginx-log-stats/test_logs/test.log";
        String fromArg = "2015-05-19";
        String toArg = "2015-05-20";

        Stream<LogRecord> logRecordsStream = parsePath(pathArg)
            .filter(logRecord -> isWithinRange(logRecord, fromArg, toArg));

        LogReport logReport = generateLogReport(logRecordsStream, pathArg, fromArg, toArg);

        assertEquals(createDefaultReport(logReport),
                """
                        ### Общая информация ###

                                Метрика        |  Значение
                        -----------------------+-----------
                         Файл(-ы) или URL      | test.log
                         Начальная дата        | 2015-05-19
                         Конечная дата         | 2015-05-20
                         Количество запросов   | 2831
                         Средний размер ответа | 782751b

                        ### Запрашиваемые ресурсы ###

                                       Ресурс               | Количество
                        ------------------------------------+------------
                          GET /downloads/product_3 HTTP/1.1 |    3
                         HEAD /downloads/product_2 HTTP/1.1 |    1
                          GET /downloads/product_1 HTTP/1.1 |    1582
                          GET /downloads/product_2 HTTP/1.1 |    1245

                        ### Коды ответа ###

                         Код |    Описание     | Количество
                        -----+-----------------+------------
                         304 | Not Modified    |    717
                         403 | Forbidden       |    1
                         404 | Not Found       |    1870
                         200 | OK              |    230
                         206 | Partial Content |    13
                        """);
    }

    @Test
    @DisplayName("Тест adoc вывода")
    void testAdocReport() {

        String pathArg = "nginx-log-stats/test_logs/test.log";
        String fromArg = "2015-05-19";

        Stream<LogRecord> logRecordsStream = parsePath(pathArg)
            .filter(logRecord -> isWithinRange(logRecord, fromArg, null));

        LogReport logReport = generateLogReport(logRecordsStream, pathArg, fromArg, null);

        createAdocReport(logReport);

        String strPathToAdocReport = "nginx-log-stats/report.adoc";
        StringBuilder content = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(strPathToAdocReport))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            System.out.println("Reading error!");
        }

        assertEquals(content.toString(),
            "### Общая информация ###" +
                "[options=header,align=center]" +
                "|=======================" +
                "| Метрика | Значение" +
                "| Файл(-ы) или URL | test.log" +
                "| Начальная дата | 2015-05-19" +
                "| Конечная дата | --" +
                "| Количество запросов | 5179" +
                "| Средний размер ответа | 909488b" +
                "|=======================" +
                "### Запрашиваемые ресурсы ###" +
                "[options=header,align=center]" +
                "|=======================" +
                "| Ресурс | Количество" +
                "| GET /downloads/product_3 HTTP/1.1 | 9" +
                "| HEAD /downloads/product_2 HTTP/1.1 | 1" +
                "| GET /downloads/product_1 HTTP/1.1 | 2973" +
                "| GET /downloads/product_2 HTTP/1.1 | 2196" +
                "|=======================" +
                "### Коды ответа ###" +
                "[options=header,align=center]" +
                "|=======================" +
                "| Код | Описание | Количество" +
                "| 304 | Not Modified | 1306" +
                "| 403 | Forbidden | 1" +
                "| 404 | Not Found | 3398" +
                "| 200 | OK | 440" +
                "| 206 | Partial Content | 34" +
                "|=======================");
    }

    @Test
    @DisplayName("Тест markdown вывода")
    void testMdReport() {

        String pathArg = "nginx-log-stats/test_logs/test.log";
        String toArg = "2015-05-19";

        Stream<LogRecord> logRecordsStream = parsePath(pathArg)
            .filter(logRecord -> isWithinRange(logRecord, null, toArg));

        LogReport logReport = generateLogReport(logRecordsStream, pathArg, null, toArg);

        createMarkdownReport(logReport);

        String strPathToMarkdownReport = "nginx-log-stats/report.md";
        StringBuilder content = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(strPathToMarkdownReport))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            System.out.println("Reading error!");
        }

        assertEquals(content.toString(),
            "### Общая информация ###" +
                "| Метрика | Значение |" +
                "|:---:|:---:|" +
                "| Файл(-ы) или URL | test.log |" +
                "| Начальная дата | -- |" +
                "| Конечная дата | 2015-05-19 |" +
                "| Количество запросов | 4821 |" +
                "| Средний размер ответа | 360711b |" +
                "### Запрашиваемые ресурсы ###" +
                "| Ресурс | Количество |" +
                "|:---:|:---:|" +
                "| GET /downloads/product_3 HTTP/1.1 | 4 |" +
                "| HEAD /downloads/product_2 HTTP/1.1 | 28 |" +
                "| GET /downloads/product_1 HTTP/1.1 | 2573 |" +
                "| GET /downloads/product_2 HTTP/1.1 | 2203 |" +
                "| HEAD /downloads/product_1 HTTP/1.1 | 13 |" +
                "### Коды ответа ###" +
                "| Код | Описание | Количество |" +
                "|:---:|:---:|:---:|" +
                "| 304 | Not Modified | 1252 |" +
                "| 403 | Forbidden | 14 |" +
                "| 404 | Not Found | 3214 |" +
                "| 200 | OK | 334 |" +
                "| 206 | Partial Content | 7 |");
    }
}
