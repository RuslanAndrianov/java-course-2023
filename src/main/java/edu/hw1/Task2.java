package edu.hw1;

public final class Task2 {

    private Task2() {}

    public static final int BASE_OF_10_SYSTEM = 10;

    static int countDigits(int n) {

        if (n != 0) {

            int abs;
            if (n == Integer.MIN_VALUE) {
                abs = Integer.MAX_VALUE;
            } else {
                // Для отрицательных чисел игнорируем знак
                abs = Math.abs(n);
            }

            int digitsCount = 0;
            while (abs > 0) {
                abs /= BASE_OF_10_SYSTEM;
                digitsCount++;
            }
            return digitsCount;
        }

        return 1;
    }
}
