package edu.hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task5.REGEXP;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask5 {

    @Test
    @DisplayName("Подходящие номерные знаки")
    void validInputs() {

        List<String> strings = new ArrayList<>();
        strings.add("В591УР716");
        strings.add("Т300ЕХ16");
        strings.add("Р001ОХ299");
        strings.add("Х777ХХ777");
        strings.add("А789ВС02");
        strings.add("О947КМ31");
        strings.add("С010НХ99");

        Pattern pattern = Pattern.compile(REGEXP);

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            assertTrue(matcher.matches());
        }
    }

    @Test
    @DisplayName("Неподходящие номерные знаки")
    void invalidInputs() {

        List<String> strings = new ArrayList<>();
        strings.add("в591ур716");
        strings.add("T300EX16"); // Английские буквы
        strings.add("Р001ОХ999");
        strings.add("123АВЕ777");
        strings.add("А123ВГ77");
        strings.add("А123ВЕ7777");
        strings.add("Х000ХХ777"); // Нельзя 000
        strings.add("фсывмцкмц");

        Pattern pattern = Pattern.compile(REGEXP);

        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            assertFalse(matcher.matches());
        }
    }
}
