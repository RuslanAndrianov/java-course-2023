package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static edu.hw5.Task2.findAllFridays13;
import static edu.hw5.Task2.findClosestFriday13;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask2 {

    @Test
    @DisplayName("Тест метода findAllFridays13")
    void testFindAllFridays13() {
        assertTrue(findAllFridays13(1925).containsAll(List.of(
            LocalDate.of(1925,2,13),
            LocalDate.of(1925,3,13),
            LocalDate.of(1925,11,13)
        )));
        assertTrue(findAllFridays13(2024).containsAll(List.of(
            LocalDate.of(2024,9,13),
            LocalDate.of(2024,12,13)
        )));
    }

    @Test
    @DisplayName("Тест метода findClosestFriday13")
    void testFindClosestFriday13() {
        assertEquals(findClosestFriday13(
            LocalDate.of(1925, 1, 12)),
            LocalDate.of(1925, 2, 13));
        assertEquals(findClosestFriday13(
            LocalDate.of(2023, 11, 13)),
            LocalDate.of(2024, 9, 13));
    }
}
