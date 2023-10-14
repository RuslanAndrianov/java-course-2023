package edu.hw1;

import java.util.Objects;

public class Task5 {

    private Task5() {}

    public static final int MIN_INT_DESCENDANT = 31_112_912;

    public static final int FIRST_TWO_DIGIT_NUMBER = 10;

    public static boolean isPalindromeDescendant(int n) {

        int abs;
        if (n == Integer.MIN_VALUE) {
            abs = MIN_INT_DESCENDANT;
        } else {
            // Для отрицательных чисел игнорируем знак
            abs = Math.abs(n);
        }

        if (abs < FIRST_TWO_DIGIT_NUMBER) {
            return true;
        } else {
            while (abs >= FIRST_TWO_DIGIT_NUMBER) {
                if (isPalindrome(abs)) {
                    return true;
                }
                abs = makeDescendant(abs);
            }
        }

        return false;
    }

    // В этот метод заходят только неотрицательные числа
    public static boolean isPalindrome(int n) {

        String[] digits = Integer.toString(n).split("");
        int numberOfComparisons = digits.length / 2;
        int end = digits.length - 1;

        for (int i = 0; i < numberOfComparisons; i++) {
            if (!Objects.equals(digits[i], digits[end - i])) {
                return false;
            }
        }

        return true;
    }

    // В этот метод заходят только натуральные числа, начиная с 10
    public static int makeDescendant(int n) {

        String[] digits = Integer.toString(n).split("");
        int numberOfAdds = digits.length / 2;

        String[] descendantDigits;
        if (digits.length % 2 == 0) {
             descendantDigits = new String[numberOfAdds];
        } else {
            descendantDigits = new String[numberOfAdds + 1];
        }

        for (int i = 0; i < numberOfAdds; i++) {
            int descendantDigit = Integer.parseInt(digits[2 * i]) + Integer.parseInt(digits[2 * i + 1]);
            descendantDigits[i] = Integer.toString(descendantDigit);
        }

        // Добавляем последнюю цифру в случае нечетной длины исходного числа
        if (digits.length % 2 != 0) {
            descendantDigits[descendantDigits.length - 1] = digits[digits.length - 1];
        }

        return Integer.parseInt(String.join("", descendantDigits));
    }
}
