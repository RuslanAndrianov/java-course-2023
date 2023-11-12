package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import static edu.hw5.Task3.parseDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestTask3 {

    @Test
    @DisplayName("Примеры из условия")
    void validTests() {
        assertEquals(parseDate("2020-10-10"), Optional.of(LocalDate.of(2020, 10, 10)));
        assertEquals(parseDate("2020-12-2"), Optional.of(LocalDate.of(2020, 12, 2)));
        assertEquals(parseDate("1/3/1976"), Optional.of(LocalDate.of(1976, 3, 1)));
        assertEquals(parseDate("1/3/20"), Optional.of(LocalDate.of(2020, 3, 1)));

        LocalDate now = LocalDate.now();
        assertEquals(parseDate("tomorrow"), Optional.of(now.plusDays(1)));
        assertEquals(parseDate("today"), Optional.of(now));
        assertEquals(parseDate("yesterday"), Optional.of(now.minusDays(1)));
        assertEquals(parseDate("1 day ago"), Optional.of(now.minusDays(1)));
        assertEquals(parseDate("2234 days ago"), Optional.of(now.minusDays(2234)));
    }

    @Test
    @DisplayName("Неподходящий формат")
    void invalidTests() {
        assertEquals(parseDate("2020 10 10"), Optional.empty());
        assertEquals(parseDate("2020,12,2"), Optional.empty());
        assertEquals(parseDate("одиннадцатое число"), Optional.empty());
    }

    @Test
    @DisplayName("Формат верный, но даты не существует")
    void errorTests() {
        try {
            parseDate("2020-13-10");
        } catch (Exception e) {
            assertInstanceOf(DateTimeException.class, e);
        }
    }
}
