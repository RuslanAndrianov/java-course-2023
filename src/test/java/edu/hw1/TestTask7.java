package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask7 {
    @Test
    void testRotateLeft() {
        assertEquals(Integer.parseInt("0110", 2), Task7.rotateLeft(Integer.parseInt("1001", 2), 2));
        assertEquals(Integer.parseInt("111000", 2), Task7.rotateLeft(Integer.parseInt("110001", 2), 5));
        assertEquals(Integer.parseInt("10", 2), Task7.rotateLeft(Integer.parseInt("10", 2), 4));
        assertEquals(Integer.parseInt("00000", 2), Task7.rotateLeft(Integer.parseInt("00000", 2), 3));
        assertEquals(Integer.parseInt("111111", 2), Task7.rotateLeft(Integer.parseInt("111111", 2), 1));
        assertEquals(Integer.MAX_VALUE, Task7.rotateLeft(Integer.MAX_VALUE, 7));
        assertEquals(-1, Task7.rotateLeft(-12, 7));
        assertEquals(-1, Task7.rotateLeft(Integer.MIN_VALUE, 7));
        assertEquals(-1, Task7.rotateLeft(Integer.parseInt("10011", 2), -2));
    }

    @Test
    void testRotateRight() {
        assertEquals(Integer.parseInt("0110", 2), Task7.rotateRight(Integer.parseInt("1001", 2), 2));
        assertEquals(Integer.parseInt("100011", 2), Task7.rotateRight(Integer.parseInt("110001", 2), 5));
        assertEquals(Integer.parseInt("10", 2), Task7.rotateRight(Integer.parseInt("10", 2), 4));
        assertEquals(Integer.parseInt("00000", 2), Task7.rotateRight(Integer.parseInt("00000", 2), 3));
        assertEquals(Integer.parseInt("111111", 2), Task7.rotateRight(Integer.parseInt("111111", 2), 1));
        assertEquals(Integer.MAX_VALUE, Task7.rotateRight(Integer.MAX_VALUE, 7));
        assertEquals(-1, Task7.rotateRight(-12, 7));
        assertEquals(-1, Task7.rotateRight(Integer.MIN_VALUE, 7));
        assertEquals(-1, Task7.rotateRight(Integer.parseInt("10011", 2), -2));
    }
}
