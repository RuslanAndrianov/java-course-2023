package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTask8 {

    private static final int[][] BOARD1 = new int[][]{
        {0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 1, 0, 1, 0},
        {0, 1, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 0, 0}
    };

    private static final int[][] BOARD2 = new int[][]{
        {1, 0, 1, 0, 1, 0, 1, 0},
        {0, 1, 0, 1, 0, 1, 0, 1},
        {0, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 1, 0, 0, 1, 0, 1},
        {1, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 1, 0, 1},
        {1, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 1, 0, 1, 0, 1}
    };

    private static final int[][] BOARD3 = new int[][]{
        {0, 0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 1, 0, 0},
        {1, 0, 0, 0, 0, 0, 0, 0}
    };

    @Test
    @DisplayName("Тест основного метода knightBoardCapture")
    void testKnightBoardCapture() {
        assertTrue(Task8.knightBoardCapture(BOARD1));
        assertFalse(Task8.knightBoardCapture(BOARD2));
        assertFalse(Task8.knightBoardCapture(BOARD3));
    }

    @Test
    @DisplayName("Тест вспомогательного метода isKnightBeatsKnight")
    void testIsKnightBeatsKnight() {
        assertFalse(Task8.isKnightBeatsKnight(4, 3, BOARD1));
        assertTrue(Task8.isKnightBeatsKnight(1, 1, BOARD2));
        assertFalse(Task8.isKnightBeatsKnight(4, 6, BOARD2));
        assertTrue(Task8.isKnightBeatsKnight(3, 2, BOARD3));
        assertFalse(Task8.isKnightBeatsKnight(0, 3, BOARD3));
    }

    @Test
    @DisplayName("Тест вспомогательного метода isKnightMoveSafe")
    void testIsKnightMoveSafe() {
        assertTrue(Task8.isKnightMoveSafe(3, 4, 2, 1, BOARD1));
        assertFalse(Task8.isKnightMoveSafe(0, 3, -2, 1, BOARD1));
        assertTrue(Task8.isKnightMoveSafe(3, 2, 1, -2, BOARD2));
        assertFalse(Task8.isKnightMoveSafe(0, 0, -1, 2, BOARD2));
        assertTrue(Task8.isKnightMoveSafe(6, 5, -2, -1, BOARD3));
        assertFalse(Task8.isKnightMoveSafe(7, 0, 2, -1, BOARD3));
    }
}
