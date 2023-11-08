package edu.hw3;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {

    private static final int[] NUMBERS = new int[] {1, 4, 5, 9, 10, 40,
        50, 90, 100, 400, 500, 900, 1000};

    private static final String[] ROMANS = new String[] {"I", "IV", "V",
        "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    private static final int MAX_ROMAN_CONVERTIBLE_INT = 3999;

    private static final int MIN_ROMAN_CONVERTIBLE_INT = 1;

    public static @NotNull String convertToRoman(int num) {
        if (num > MAX_ROMAN_CONVERTIBLE_INT || num < MIN_ROMAN_CONVERTIBLE_INT) {
            return "";
        }

        int numCopy = num;
        StringBuilder romanNum = new StringBuilder();
        while (numCopy > 0) {
            for (int i = NUMBERS.length - 1; i >= 0; i--) {
                if (numCopy - NUMBERS[i] >= 0) {
                    numCopy -= NUMBERS[i];
                    romanNum.append(ROMANS[i]);
                    break;
                }
            }
        }
        return romanNum.toString();
    }
}
