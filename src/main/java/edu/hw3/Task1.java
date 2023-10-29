package edu.hw3;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"HideUtilityClassConstructor", "ConstantName"})

public class Task1 {

    private static final int ASCII_A = 65;

    private static final int ASCII_Z = 90;

    private static final int ASCII_a = 97;

    private static final int ASCII_z = 122;

    public static @NotNull String atbash(@NotNull String inputStr) {
        char[] chars = inputStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= ASCII_A && chars[i] <= ASCII_Z) {
                chars[i] = (char) (ASCII_A + ASCII_Z - chars[i]);
                continue;
            }
            if (chars[i] >= ASCII_a && chars[i] <= ASCII_z) {
                chars[i] = (char) (ASCII_a + ASCII_z - chars[i]);
            }
        }
        return new String(chars);
    }
}
