package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTask3 {
    @Test
    @DisplayName("Можно вложить")
    void caseTrue() {
        assertTrue(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 6}));
        assertTrue(Task3.isNestable(new int[]{3, 1}, new int[]{4, 0}));
        assertTrue(Task3.isNestable(new int[]{2, 2, 2, 2}, new int[]{1, 6}));
    }

    @Test
    @DisplayName("Нельзя вложить")
    void caseFalse() {
        assertFalse(Task3.isNestable(new int[]{9, 9, 8}, new int[]{8, 9}));
        assertFalse(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{2, 3}));
        assertFalse(Task3.isNestable(new int[]{1, 1, 1, 1}, new int[]{2, 3}));
    }

    @Test
    @DisplayName("Пустые массивы")
    void caseNull() {
        assertTrue(Task3.isNestable(null, new int[]{8, 9}));
        assertFalse(Task3.isNestable(new int[]{1, 2, 3, 4}, null));
        assertFalse(Task3.isNestable(null, null));
    }
}
