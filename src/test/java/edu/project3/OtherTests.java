package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import static edu.project3.DefaultGenerator.createDefaultReport;
import static edu.project3.HelpFunctions.generateLogReport;
import static edu.project3.HelpFunctions.isWithinRange;
import static edu.project3.Parsers.parsePath;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtherTests {

    @Test
    @DisplayName("Данных в таком интервале дат нет")
    void testPathToFile() {

        String pathArg = "nginx-log-stats/test_logs/test.log";
        String fromArg = "2015-05-15";
        String toArg = "2015-05-17";

        Stream<LogRecord> logRecordsStream =
            parsePath(pathArg).filter(logRecord -> isWithinRange(logRecord, fromArg, toArg));

        LogReport logReport = generateLogReport(logRecordsStream, pathArg, fromArg, toArg);

        assertEquals(createDefaultReport(logReport), """
            ### Общая информация ###

                    Метрика        |  Значение
            -----------------------+-----------
             Файл(-ы) или URL      | test.log
             Начальная дата        | 2015-05-15
             Конечная дата         | 2015-05-17
             Количество запросов   | 0
             Средний размер ответа | 0b

            """);
    }
}
