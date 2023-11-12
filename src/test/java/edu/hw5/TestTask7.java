package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.hw5.Task7.REGEXP1;
import static edu.hw5.Task7.REGEXP2;
import static edu.hw5.Task7.REGEXP3;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask7 {

    @Test
    @DisplayName("Подходящие строки для регулярки 1")
    void validInputs1() {

        List<String> strings = new ArrayList<>();
        strings.add("110");
        strings.add("00000");
        strings.add("1000111010");
        strings.add("0101");
        strings.add("000");
        strings.add("11011");

        Pattern pattern = Pattern.compile(REGEXP1);

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            assertTrue(matcher.matches());
        }
    }

    @Test
    @DisplayName("Неподходящие строки для регулярки 1")
    void invalidInputs1() {

        List<String> strings = new ArrayList<>();
        strings.add("101");
        strings.add("11111");
        strings.add("22222");
        strings.add("103421");
        strings.add("нуль");
        strings.add("acsdcw");

        Pattern pattern = Pattern.compile(REGEXP1);

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            assertFalse(matcher.matches());
        }
    }

    @Test
    @DisplayName("Подходящие строки для регулярки 2")
    void validInputs2() {

        List<String> strings = new ArrayList<>();
        strings.add("101");
        strings.add("11");
        strings.add("00");
        strings.add("1000111011");
        strings.add("011100");
        strings.add("0");
        strings.add("1");

        Pattern pattern = Pattern.compile(REGEXP2);

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            assertTrue(matcher.matches());
        }
    }

    @Test
    @DisplayName("Неподходящие строки для регулярки 2")
    void invalidInputs2() {

        List<String> strings = new ArrayList<>();
        strings.add("11110");
        strings.add("1010110");
        strings.add("1a1");
        strings.add("22222");
        strings.add("нуль");
        strings.add("acsdcw");

        Pattern pattern = Pattern.compile(REGEXP2);

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            assertFalse(matcher.matches());
        }
    }

    @Test
    @DisplayName("Подходящие строки для регулярки 3")
    void validInputs3() {

        List<String> strings = new ArrayList<>();
        strings.add("0");
        strings.add("1");
        strings.add("00");
        strings.add("01");
        strings.add("10");
        strings.add("11");
        strings.add("000");
        strings.add("001");
        strings.add("010");
        strings.add("011");
        strings.add("100");
        strings.add("101");
        strings.add("110");
        strings.add("111");

        Pattern pattern = Pattern.compile(REGEXP3);

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            assertTrue(matcher.matches());
        }
    }

    @Test
    @DisplayName("Неподходящие строки для регулярки 3")
    void invalidInputs3() {

        List<String> strings = new ArrayList<>();
        strings.add("11110");
        strings.add("1010110");
        strings.add("1a1");
        strings.add("22222");
        strings.add("нуль");
        strings.add("acsdcw");

        Pattern pattern = Pattern.compile(REGEXP3);

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            assertFalse(matcher.matches());
        }
    }
}
