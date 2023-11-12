package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static edu.hw5.Task1.calculateAvgTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {

    @Test
    @DisplayName("Пример из условия")
    void test1() {

        List<String> times = new ArrayList<>();
        times.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        times.add("2022-04-01, 21:30 - 2022-04-02, 01:20");

        assertEquals(calculateAvgTime(times), "3ч 40м");
    }

    @Test
    @DisplayName("Нецелое число минут")
    void test2() {

        List<String> times = new ArrayList<>();
        times.add("2022-03-12, 23:23 - 2022-03-12, 23:47");
        times.add("2022-04-01, 21:30 - 2022-04-01, 21:43");

        // 37 : 2 == 18,5
        assertEquals(calculateAvgTime(times), "0ч 18м");
    }

    @Test
    @DisplayName("Долгие сеансы")
    void test3() {

        List<String> times = new ArrayList<>();
        times.add("2022-03-12, 00:00 - 2022-04-12, 00:00");
        times.add("2022-04-01, 21:30 - 2022-04-11, 21:40");

        assertEquals(calculateAvgTime(times), "492ч 05м");
    }

    @Test
    @DisplayName("Пустой список")
    void test4() {

        List<String> times = new ArrayList<>();

        assertEquals(calculateAvgTime(times), "0ч 00м");
    }
}
