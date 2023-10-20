package edu.hw1;

import java.util.Arrays;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {

     public static boolean isNestable(int[] arr1, int[] arr2) {
        // Ничего нельзя вложить в пустой массив
        if (arr2 == null) {
            return false;
        }
        // Пустой массив можно вложить в любой массив
        if (arr1 == null) {
            return true;
        }

        int minArr1 = Integer.MIN_VALUE;
        int maxArr1 = Integer.MIN_VALUE;
        int minArr2 = Integer.MIN_VALUE;
        int maxArr2 = Integer.MIN_VALUE;

        if (Arrays.stream(arr1).min().isPresent()) {
            minArr1 = Arrays.stream(arr1).min().getAsInt();
        }
        if (Arrays.stream(arr1).max().isPresent()) {
            maxArr1 = Arrays.stream(arr1).max().getAsInt();
        }
        if (Arrays.stream(arr2).min().isPresent()) {
            minArr2 = Arrays.stream(arr2).min().getAsInt();
        }
        if (Arrays.stream(arr2).max().isPresent()) {
            maxArr2 = Arrays.stream(arr2).max().getAsInt();
        }

        return (minArr1 > minArr2) && (maxArr1 < maxArr2);
    }
}
