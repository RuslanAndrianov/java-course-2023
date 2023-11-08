package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static edu.hw3.Task3.freqDict;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask3 {

    @Test
    @DisplayName("Тест 1 из условия")
    void test1() {
        List<String> list = Arrays.asList("a", "bb", "a", "bb");

        HashMap<String, Integer> result = new HashMap<>();
        result.put("a", 2);
        result.put("bb", 2);

        assertEquals(freqDict(list), result);
    }

    @Test
    @DisplayName("Тест 2 из условия")
    void test2() {
        List<String> list = Arrays.asList("this", "and", "that", "and");

        HashMap<String, Integer> result = new HashMap<>();
        result.put("this", 1);
        result.put("and", 2);
        result.put("that", 1);

        assertEquals(freqDict(list), result);
    }

    @Test
    @DisplayName("Тест 3 из условия")
    void test3() {
        List<String> list = Arrays.asList("код", "код", "код", "bug");

        HashMap<String, Integer> result = new HashMap<>();
        result.put("код", 3);
        result.put("bug", 1);

        assertEquals(freqDict(list), result);
    }

    @Test
    @DisplayName("Тест 4 из условия")
    void test4() {
        List<Integer> list = Arrays.asList(1, 1, 2, 2);

        HashMap<Integer, Integer> result = new HashMap<>();
        result.put(1, 2);
        result.put(2, 2);

        assertEquals(freqDict(list), result);
    }

    @Test
    @DisplayName("Тест 5")
    void test5() {
        List<Boolean> list = Arrays.asList(true, false, true, true, false);

        HashMap<Boolean, Integer> result = new HashMap<>();
        result.put(true, 3);
        result.put(false, 2);

        assertEquals(freqDict(list), result);
    }
}
