package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {

     public static String fixString(String input) {
        if (input != null) {

            int numberOfSwaps = input.length() / 2;
            String[] letters = input.split("");
            String fixedStr;
            String temp;

            for (int i = 0; i < numberOfSwaps; i++) {
                temp = letters[2 * i];
                letters[2 * i] = letters[2 * i + 1];
                letters[2 * i + 1] = temp;
            }

            fixedStr = String.join("", letters);
            return fixedStr;
        }
        return "";
    }
}
