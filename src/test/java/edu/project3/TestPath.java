package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import static edu.project3.DefaultGenerator.createDefaultReport;
import static edu.project3.HelpFunctions.generateLogReport;
import static edu.project3.HelpFunctions.isWithinRange;
import static edu.project3.Parsers.parsePath;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPath {

    @Test
    @DisplayName("Путь до файла")
    void testPathToFile() {

        String pathArg = "nginx-log-stats/test_logs/test.log";
        String fromArg = "2015-05-19";
        String toArg = "2015-05-20";

        Stream<LogRecord> logRecordsStream =
            parsePath(pathArg).filter(logRecord -> isWithinRange(logRecord, fromArg, toArg));

        LogReport logReport = generateLogReport(logRecordsStream, pathArg, fromArg, toArg);

        assertEquals(createDefaultReport(logReport), """
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
    @DisplayName("Путь до директории")
    void testPathToDir() {

        String pathArg = "nginx-log-stats/test_logs";
        String fromArg = "2015-05-17";
        String toArg = "2015-05-19";

        Stream<LogRecord> logRecordsStream =
            parsePath(pathArg).filter(logRecord -> isWithinRange(logRecord, fromArg, toArg));

        LogReport logReport = generateLogReport(logRecordsStream, pathArg, fromArg, toArg);

        assertEquals(
            createDefaultReport(logReport),
            """
                ### Общая информация ###

                        Метрика        |  Значение
                -----------------------+-----------
                 Файл(-ы) или URL      | test.log
                                       | test2.log
                                       | test3.log
                 Начальная дата        | 2015-05-17
                 Конечная дата         | 2015-05-19
                 Количество запросов   | 10642
                 Средний размер ответа | 326840b

                ### Запрашиваемые ресурсы ###

                               Ресурс               | Количество
                ------------------------------------+------------
                  GET /downloads/product_3 HTTP/1.1 |    8
                 HEAD /downloads/product_2 HTTP/1.1 |    69
                  GET /downloads/product_1 HTTP/1.1 |    5623
                  GET /downloads/product_2 HTTP/1.1 |    4913
                 HEAD /downloads/product_1 HTTP/1.1 |    29

                ### Коды ответа ###

                 Код |    Описание     | Количество
                -----+-----------------+------------
                 304 | Not Modified    |    2778
                 403 | Forbidden       |    30
                 404 | Not Found       |    7116
                 200 | OK              |    703
                 206 | Partial Content |    15
                """
        );
    }

    @Test
    @DisplayName("URL")
    void testPathToURL() {

        String pathArg =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        String fromArg = "2015-05-17";
        String toArg = "2015-05-19";

        Stream<LogRecord> logRecordsStream =
            parsePath(pathArg).filter(logRecord -> isWithinRange(logRecord, fromArg, toArg));

        LogReport logReport = generateLogReport(logRecordsStream, pathArg, fromArg, toArg);

        assertEquals(
            createDefaultReport(logReport),
            """
                ### Общая информация ###

                        Метрика        |  Значение
                -----------------------+-----------
                 Файл(-ы) или URL      | https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs
                 Начальная дата        | 2015-05-17
                 Конечная дата         | 2015-05-19
                 Количество запросов   | 4821
                 Средний размер ответа | 360711b

                ### Запрашиваемые ресурсы ###

                               Ресурс               | Количество
                ------------------------------------+------------
                  GET /downloads/product_3 HTTP/1.1 |    4
                 HEAD /downloads/product_2 HTTP/1.1 |    28
                  GET /downloads/product_1 HTTP/1.1 |    2573
                  GET /downloads/product_2 HTTP/1.1 |    2203
                 HEAD /downloads/product_1 HTTP/1.1 |    13

                ### Коды ответа ###

                 Код |    Описание     | Количество
                -----+-----------------+------------
                 304 | Not Modified    |    1252
                 403 | Forbidden       |    14
                 404 | Not Found       |    3214
                 200 | OK              |    334
                 206 | Partial Content |    7
                 """
        );
    }
}
