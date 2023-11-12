package edu.hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task8.REGEXP1;
import static edu.hw5.Task8.REGEXP2;
import static edu.hw5.Task8.REGEXP3;
import static edu.hw5.Task8.REGEXP4;
import static edu.hw5.Task8.REGEXP5;
import static edu.hw5.Task8.REGEXP6;
import static edu.hw5.Task8.REGEXP7;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask8 {

    // Список чисел от 0 до 1111
    List<String> strings = new ArrayList<>();
    {
        strings.add("0");       //0
        strings.add("1");       //1
        strings.add("00");      //2
        strings.add("01");      //3
        strings.add("10");      //4
        strings.add("11");      //5
        strings.add("000");     //6
        strings.add("001");     //7
        strings.add("010");     //8
        strings.add("011");     //9
        strings.add("100");     //10
        strings.add("101");     //11
        strings.add("110");     //12
        strings.add("111");     //13
        strings.add("0000");    //14
        strings.add("0001");    //15
        strings.add("0010");    //16
        strings.add("0011");    //17
        strings.add("0100");    //18
        strings.add("0101");    //19
        strings.add("0110");    //20
        strings.add("0111");    //21
        strings.add("1000");    //22
        strings.add("1001");    //23
        strings.add("1010");    //24
        strings.add("1011");    //25
        strings.add("1100");    //26
        strings.add("1101");    //27
        strings.add("1110");    //28
        strings.add("1111");    //29
    }

    @Test
    @DisplayName("Тест регулярки 1")
    void test1() {
        Pattern pattern = Pattern.compile(REGEXP1);
        for (int i = 0; i < strings.size(); i++) {
            Matcher matcher = pattern.matcher(strings.get(i));
            if (i == 0 || i == 1 || (i >= 6 && i <= 13)) {
                assertTrue(matcher.matches());
            } else {
                assertFalse(matcher.matches());
            }
        }
    }

    @Test
    @DisplayName("Тест регулярки 2")
    void test2() {
        Pattern pattern = Pattern.compile(REGEXP2);
        for (int i = 0; i < strings.size(); i++) {
            Matcher matcher = pattern.matcher(strings.get(i));
            if (i == 0 || (i >= 4 && i <= 9) || (i >= 22 && i <= 29)) {
                assertTrue(matcher.matches());
            } else {
                assertFalse(matcher.matches());
            }
        }
    }

    @Test
    @DisplayName("Тест регулярки 3")
    void test3() {
        Pattern pattern = Pattern.compile(REGEXP3);
        for (int i = 0; i < strings.size(); i++) {
            Matcher matcher = pattern.matcher(strings.get(i));
            if (i == 6 || i == 15 || i == 16 || i == 18 || i == 22) {
                assertTrue(matcher.matches());
            } else {
                assertFalse(matcher.matches());
            }
        }
    }

    @Test
    @DisplayName("Тест регулярки 4")
    void test4() {
        Pattern pattern = Pattern.compile(REGEXP4);
        for (int i = 0; i < strings.size(); i++) {
            Matcher matcher = pattern.matcher(strings.get(i));
            if (i == 5 || i == 13) {
                assertFalse(matcher.matches());
            } else {
                assertTrue(matcher.matches());
            }
        }
    }

    @Test
    @DisplayName("Тест регулярки 5")
    void test5() {
        Pattern pattern = Pattern.compile(REGEXP5);
        for (int i = 0; i < strings.size(); i++) {
            Matcher matcher = pattern.matcher(strings.get(i));
            if (i == 1 || i == 4 || i == 5 || i == 11 || i == 13
                || i == 24 || i == 25 || i == 28 || i == 29) {
                assertTrue(matcher.matches());
            } else {
                assertFalse(matcher.matches());
            }
        }
    }

    @Test
    @DisplayName("Тест регулярки 6")
    void test6() {
        Pattern pattern = Pattern.compile(REGEXP6);
        for (int i = 0; i < strings.size(); i++) {
            Matcher matcher = pattern.matcher(strings.get(i));
            if (i == 2 || (i >= 6 && i <= 8) || i == 10
                || (i >= 14 && i <= 16) || i == 18 || i == 22) {
                assertTrue(matcher.matches());
            } else {
                assertFalse(matcher.matches());
            }
        }
    }

    @Test
    @DisplayName("Тест регулярки 7")
    void test7() {
        Pattern pattern = Pattern.compile(REGEXP7);
        for (int i = 0; i < strings.size(); i++) {
            Matcher matcher = pattern.matcher(strings.get(i));
            if (i == 5 || i == 9 || i == 12 || i == 13 || i == 17
            || i == 20 || i == 21 || (i >= 25 && i <= 29)) {
                assertFalse(matcher.matches());
            } else {
                assertTrue(matcher.matches());
            }
        }
    }
}
