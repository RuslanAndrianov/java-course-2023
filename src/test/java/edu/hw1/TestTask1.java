package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {
    @Test
    @DisplayName("Нулевое время")
    void caseZero() {
        assertEquals(0, Task1.minutesToSeconds("00:00"));
        assertEquals(0, Task1.minutesToSeconds("0000:00"));
        assertEquals(0, Task1.minutesToSeconds("  0000000  :  00  "));
    }

    @Test
    @DisplayName("Верный ввод")
    void validInputs() {
        assertEquals(12 * 60 + 34, Task1.minutesToSeconds("12:34"));
        assertEquals(53 * 60 + 56, Task1.minutesToSeconds("53:56"));
        assertEquals(699 * 60 + 34, Task1.minutesToSeconds("699:34"));
    }

    @Test
    @DisplayName("Верный ввод с пробелами")
    void validInputsWithSpaces() {
        assertEquals(12 * 60 + 34, Task1.minutesToSeconds("    12   :    34   "));
        assertEquals(53 * 60 + 56, Task1.minutesToSeconds("53:        56"));
        assertEquals(699 * 60 + 34, Task1.minutesToSeconds("699    :34"));
    }

    @Test
    @DisplayName("Верный ввод с ведущими нулями в минутах")
    void validInputsWithZeros() {
        assertEquals(59, Task1.minutesToSeconds("000000000:59"));
        assertEquals(9 * 60, Task1.minutesToSeconds("0000000009:00"));
        assertEquals(9900 * 60 + 5, Task1.minutesToSeconds("00009900:05"));
    }

    @Test
    @DisplayName("Неверное количество минут/секунд")
    void invalidMinsOrSecs() {
        assertEquals(-1, Task1.minutesToSeconds("5:00"));
        assertEquals(-1, Task1.minutesToSeconds("13:67"));
        assertEquals(-1, Task1.minutesToSeconds("37:9"));
        assertEquals(-1, Task1.minutesToSeconds("43:194"));
    }

    @Test
    @DisplayName("Неверный формат ввода")
    void invalidInput() {
        assertEquals(-1, Task1.minutesToSeconds("15:"));
        assertEquals(-1, Task1.minutesToSeconds(":15"));
        assertEquals(-1, Task1.minutesToSeconds(":"));
        assertEquals(-1, Task1.minutesToSeconds("15%34"));
        assertEquals(-1, Task1.minutesToSeconds("ab:cd"));
        assertEquals(-1, Task1.minutesToSeconds(""));
        assertEquals(-1, Task1.minutesToSeconds(null));
    }

    @Test
    @DisplayName("Максимально допустимое время")
    void maxInput() {
        assertEquals(99999 * 60 + 59, Task1.minutesToSeconds("99999:59"));
    }
}
