package edu.hw1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class Task6 {
    private Task6() {}

    public static final int NUMBER_OF_DIGITS = 4;

    public static final int CHECK_DIGITS_FOR_SAMENESS = 1111;

    public static final int KAPREKAR_CONSTANT = 6174;

    public static int countK(int n) {

        int count = -1;

        if ((n > 0) && (Task2.countDigits(n) == NUMBER_OF_DIGITS) && (n % CHECK_DIGITS_FOR_SAMENESS != 0)) {

            if (n == KAPREKAR_CONSTANT) {
                count = 0;
            } else {
                count = recursiveCountK(n, 1);
            }
        }

        return count;
    }

    public static int recursiveCountK(int n, int count) {
        int copyCount = count;
        int min = makeMinNumberFromDigits(n);
        int max = makeMaxNumberFromDigits(n);
        int dif = max - min;
        if (dif != KAPREKAR_CONSTANT) {
            copyCount = recursiveCountK(dif, ++copyCount);
        }
        return copyCount;
    }

    public static int makeMaxNumberFromDigits(int n) {

        int[] digits = intToAscSortedArrOfDigits(n);

        int max = 0;

        // Склеиваем массив цифр в максимальное число
        int base = 1;
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            max += digits[i] * base;
            base *= Task2.BASE_OF_10_SYSTEM;
        }

        return max;
    }

    public static int makeMinNumberFromDigits(int n) {

        int[] digits = intToAscSortedArrOfDigits(n);

        int min = 0;

        // Склеиваем массив цифр в минимальное число
        int base = 1;
        for (int i = NUMBER_OF_DIGITS - 1; i >= 0; i--) {
            min += digits[i] * base;
            base *= Task2.BASE_OF_10_SYSTEM;
        }

        return min;
    }

    public static int @NotNull [] intToAscSortedArrOfDigits(int n) {

        int[] digits = new int[NUMBER_OF_DIGITS];
        int copyN = n;

        // Переводим число в массив цифр
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            digits[NUMBER_OF_DIGITS - 1 - i] = copyN % Task2.BASE_OF_10_SYSTEM;
            copyN /= Task2.BASE_OF_10_SYSTEM;
        }

        // Сортируем массив цифр от меньшей цифры к большей
        Arrays.sort(digits);

        return digits;
    }
}
