package edu.project1;

import org.jetbrains.annotations.NotNull;

public abstract class ProcessingGame {

    public static String getRandomWordFromDictionary(String @NotNull [] dictionary) {
        return dictionary[(int) (Math.random() * dictionary.length)];
    }

    public static boolean userInputIsCorrect(@NotNull String userGuess) {
        return (userGuess.matches("^[A-Za-z]$") || isUserGaveUp(userGuess));
    }

    public static boolean isLetterWasAlreadyChosen(@NotNull String userGuess, String @NotNull [] letters) {
        if (isUserGaveUp(userGuess)) {
            return false;
        }

        for (String letter : letters) {
            if (userGuess.equalsIgnoreCase(letter)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isUserGaveUp(@NotNull String userGuess) {
        return userGuess.equalsIgnoreCase("!STOP!");
    }

    public static boolean isUserGuessInWord(@NotNull String userGuess, @NotNull String word) {
        return (word.contains(userGuess.toUpperCase()) || word.contains(userGuess.toLowerCase()));
    }

    public static @NotNull String updateStarWord(String userGuess, @NotNull String word, @NotNull String starWord) {
        String[] letters = word.split("");
        String[] lettersInStarWord = starWord.split("");

        for (int i = 0; i < word.length(); i++) {
            if (userGuess.equalsIgnoreCase(letters[i])) {
                lettersInStarWord[i] = userGuess.toLowerCase();
            }
        }
        return String.join("", lettersInStarWord);
    }

    @SuppressWarnings("MagicNumber")
    public static String getHangman(int fails) {
        return switch (fails) {
            case 1 -> """

                        ______
                       |      |
                       |      ◯
                       |
                       |
                    ___|___
                    """;
            case 2 -> """

                        ______
                       |      |
                       |      ◯
                       |      |
                       |
                    ___|___
                    """;
            case 3 -> """

                        ______
                       |      |
                       |      ◯
                       |     /|
                       |
                    ___|___
                    """;
            case 4 -> """

                        ______
                       |      |
                       |      ◯
                       |     /|\\
                       |
                    ___|___
                    """;
            case 5 -> """

                        ______
                       |      |
                       |      ◯
                       |     /|\\
                       |     /
                    ___|___
                    """;
            case 6 -> """

                        ______
                       |      |
                       |      ◯
                       |     /|\\
                       |     / \\
                    ___|___
                    """;
            default -> "";
        };
    }

    public static void updateAvailableLetters(String userGuess, String @NotNull [] letters) {
        for (int i = 0; i < letters.length; i++) {
            if (userGuess.equalsIgnoreCase(letters[i])) {
                letters[i] = "#";
                break;
            }
        }
    }
}
