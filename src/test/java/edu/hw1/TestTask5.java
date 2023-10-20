package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTask5 {

    @Test
    @DisplayName("Тест основного метода isPalindromeDescendant: ответ true")
    void testTrueIsPalindromeDescendant() {
        assertTrue(Task5.isPalindromeDescendant(11211230));
        assertTrue(Task5.isPalindromeDescendant(13001120));
        assertTrue(Task5.isPalindromeDescendant(23336014));
        assertTrue(Task5.isPalindromeDescendant(11));
        assertTrue(Task5.isPalindromeDescendant(Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("Тест основного метода isPalindromeDescendant: ответ false")
    void testFalseIsPalindromeDescendant() {
        assertFalse(Task5.isPalindromeDescendant(124));
        assertFalse(Task5.isPalindromeDescendant(12345));
        assertFalse(Task5.isPalindromeDescendant(997089));
        assertFalse(Task5.isPalindromeDescendant(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Тест основного метода isPalindromeDescendant: проверка отрицательных чисел")
    void testNegIsPalindromeDescendant() {
        assertTrue(Task5.isPalindromeDescendant(-11211230));
        assertTrue(Task5.isPalindromeDescendant(-13001120));
        assertTrue(Task5.isPalindromeDescendant(Integer.MIN_VALUE));
        assertFalse(Task5.isPalindromeDescendant(-997089));
        assertFalse(Task5.isPalindromeDescendant(-Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Тест вспомогательного метода isPalindrome")
    void testIsPalindrome() {
        assertTrue(Task5.isPalindrome(0));
        assertTrue(Task5.isPalindrome(5));
        assertTrue(Task5.isPalindrome(11));
        assertTrue(Task5.isPalindrome(12321));
        assertFalse(Task5.isPalindrome(12));
        assertFalse(Task5.isPalindrome(1234322));
        assertFalse(Task5.isPalindrome(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Тест вспомогательного метода makeDescendant")
    void testMakeDescendant() {
        assertEquals(1, Task5.makeDescendant(10));
        assertEquals(37, Task5.makeDescendant(1234));
        assertEquals(375, Task5.makeDescendant(12345));
        assertEquals(3711159, Task5.makeDescendant(123456789));
        assertEquals(Task5.MIN_INT_DESCENDANT - 1, Task5.makeDescendant(Integer.MAX_VALUE));
    }
}
