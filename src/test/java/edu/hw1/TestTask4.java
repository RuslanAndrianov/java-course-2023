package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask4 {

    @Test
    @DisplayName("Четная длина строки")
    void evenStrLength() {
        assertEquals("214365", Task4.fixString("123456"));
        assertEquals("This is a mixed up string.", Task4.fixString("hTsii  s aimex dpus rtni.g"));
        assertEquals("badcfe", Task4.fixString("abcdef"));
    }

    @Test
    @DisplayName("Нечетная длина строки")
    void oddStrLength() {
        assertEquals("21435", Task4.fixString("12345"));
        assertEquals("This is a mixed up string", Task4.fixString("hTsii  s aimex dpus rtnig"));
        assertEquals("bacde", Task4.fixString("abdce"));
    }

    @Test
    @DisplayName("Краевые случаи")
    void edgeCases() {
        assertEquals("", Task4.fixString(""));
        assertEquals("", Task4.fixString(null));
    }
}
