package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask6 {
    @Test
    @DisplayName("Тест основного метода countK: верный ввод")
    void validInputs() {
        assertEquals(0, Task6.countK(6174));
        assertEquals(3, Task6.countK(3524));
        assertEquals(5, Task6.countK(6621));
        assertEquals(4, Task6.countK(6554));
        assertEquals(3, Task6.countK(1234));
        assertEquals(6, Task6.countK(6661));
    }

    @Test
    @DisplayName("Тест основного метода countK: неверный ввод")
    void invalidInputs() {
        assertEquals(-1, Task6.countK(0));
        assertEquals(-1, Task6.countK(12));
        assertEquals(-1, Task6.countK(6666));
        assertEquals(-1, Task6.countK(-1198));
        assertEquals(-1, Task6.countK(Integer.MAX_VALUE));
        assertEquals(-1, Task6.countK(Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("Тест вспомогательного метода makeMaxNumberFromDigits")
    void testMakeMaxNumberFromDigits() {
        assertEquals(7641, Task6.makeMaxNumberFromDigits(6174));
        assertEquals(5432, Task6.makeMaxNumberFromDigits(3524));
        assertEquals(6621, Task6.makeMaxNumberFromDigits(6621));
        assertEquals(6661, Task6.makeMaxNumberFromDigits(6661));
        assertEquals(9000, Task6.makeMaxNumberFromDigits(9000));
        assertEquals(9100, Task6.makeMaxNumberFromDigits(9100));
        assertEquals(9420, Task6.makeMaxNumberFromDigits(9024));
    }

    @Test
    @DisplayName("Тест вспомогательного метода makeMinNumberFromDigits")
    void testMakeMinNumberFromDigits() {
        assertEquals(1467, Task6.makeMinNumberFromDigits(6174));
        assertEquals(2345, Task6.makeMinNumberFromDigits(3524));
        assertEquals(1266, Task6.makeMinNumberFromDigits(6621));
        assertEquals(1666, Task6.makeMinNumberFromDigits(6661));
        assertEquals(9, Task6.makeMinNumberFromDigits(9000));
        assertEquals(19, Task6.makeMinNumberFromDigits(9100));
        assertEquals(249, Task6.makeMinNumberFromDigits(9024));
    }
}
