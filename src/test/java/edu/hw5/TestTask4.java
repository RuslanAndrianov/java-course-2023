package edu.hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task4.REGEXP;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask4 {

    @Test
    @DisplayName("Подходящие пароли")
    void validPasswords() {

        List<String> strings = new ArrayList<>();
        strings.add("abcd!");
        strings.add("nwrfh#gwbeh");
        strings.add("%123456");
        strings.add("jwend|evfq");
        strings.add("^огурец1996$");

        Pattern pattern = Pattern.compile(REGEXP);

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            assertTrue(matcher.matches());
        }
    }

    @Test
    @DisplayName("Неподходящие пароли")
    void invalidPasswords() {

        List<String> strings = new ArrayList<>();
        strings.add("abcd");
        strings.add("nwrfhgwbeh");
        strings.add("123456");
        strings.add("огурец1996");
        strings.add("");

        Pattern pattern = Pattern.compile(REGEXP);

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            assertFalse(matcher.matches());
        }
    }
}
