package edu.hw1;

public class Task8 {
    private Task8() {}

    public static final int[] X_MOVES = new int[] {1, 2, 2, 1, -1, -2, -2, -1};

    public static final int[] Y_MOVES = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};

    public static boolean knightBoardCapture(int[][] board) {
        int height = board.length;
        int length = board[0].length;
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                if (board[y][x] == 1 && isKnightBeatsKnight(x, y, board)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isKnightBeatsKnight(int x, int y, int[][] board) {
        for (int i = 0; i < X_MOVES.length; i++) {
            if (isKnightMoveSafe(x, y, X_MOVES[i], Y_MOVES[i], board)) {
                if (board[y + Y_MOVES[i]][x + X_MOVES[i]] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isKnightMoveSafe(int x, int y, int dx, int dy, int[][] board) {
        int height = board.length;
        int length = board[0].length;

        return ((x + dx >= 0) && (x + dx < length) && (y + dy >= 0) && (y + dy < height));
    }
}
