package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task2.clusterize;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestTask2 {

    @Test
    @DisplayName("Строки, состоящие только из скобок")
    void validInputs() {
        assertArrayEquals(new String[]{"()", "()", "()"}, clusterize("()()()"));
        assertArrayEquals(new String[]{"((()))"}, clusterize("((()))"));
        assertArrayEquals(new String[]{"((()))", "(())", "()", "()", "(()())"}, clusterize("((()))(())()()(()())"));
        assertArrayEquals(new String[]{"((())())", "(()(()()))"}, clusterize("((())())(()(()()))"));
    }

    @Test
    @DisplayName("Строки с пробельными символами в начале и в конце")
    void validTrimInputs() {
        assertArrayEquals(new String[]{"()", "()", "()"}, clusterize("       ()()()"));
        assertArrayEquals(new String[]{"((()))"}, clusterize("  ((()))  "));
        assertArrayEquals(new String[]{"((())())", "(()(()()))"}, clusterize("((())())(()(()()))           "));
        assertArrayEquals(new String[]{"((())())", "(()(()()))"}, clusterize("((())())(()(()()))\n"));
    }

    @Test
    @DisplayName("Строки c пробелами в середине")
    void validSpaceInputs() {
        assertArrayEquals(new String[]{"()", "()", "()"}, clusterize("(     )(    )(  )"));
        assertArrayEquals(new String[]{"((()))"}, clusterize("( ( ( ) ) )"));
        assertArrayEquals(new String[]{"((())())", "(()(()()))"}, clusterize("((())  () )(() (()()))"));
        assertArrayEquals(new String[]{"((())())", "(()(()()))"}, clusterize("( ( ( ) ) ( ) )(()(()()))"));
    }

    @Test
    @DisplayName("Неверные строки")
    void invalidInputs() {
        assertArrayEquals(new String[]{}, clusterize(""));
        assertArrayEquals(new String[]{}, clusterize("(a)()()"));
        assertArrayEquals(new String[]{}, clusterize("((()))(())()()(()())0"));
    }
}
