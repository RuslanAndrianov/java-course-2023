package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {
    @Test
    @DisplayName("Нуль")
    void caseZero() {
        assertEquals(1, Task2.countDigits(0));
    }

    @Test
    @DisplayName("Положительные числа")
    void posInputs() {
        assertEquals(1, Task2.countDigits(1));
        assertEquals(1, Task2.countDigits(5));
        assertEquals(2, Task2.countDigits(13));
        assertEquals(3, Task2.countDigits(999));
        assertEquals(5, Task2.countDigits(57210));
        assertEquals(8, Task2.countDigits(30298781));
    }

    @Test
    @DisplayName("Отрицательные числа")
    void negInputs() {
        assertEquals(1, Task2.countDigits(-1));
        assertEquals(1, Task2.countDigits(-5));
        assertEquals(2, Task2.countDigits(-13));
        assertEquals(3, Task2.countDigits(-999));
        assertEquals(5, Task2.countDigits(-57210));
        assertEquals(8, Task2.countDigits(-30298781));
    }

    @Test
    @DisplayName("Краевые случаи")
    void edgeCases() {
        assertEquals(10, Task2.countDigits(Integer.MAX_VALUE));
        assertEquals(10, Task2.countDigits(Integer.MIN_VALUE));
    }
}
