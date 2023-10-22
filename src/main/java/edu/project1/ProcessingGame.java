package edu.project1;

@SuppressWarnings("HideUtilityClassConstructor")
public class ProcessingGame {

    public static String getRandomWordFromDictionary(String[] dictionary) {
        return dictionary[(int) (Math.random() * dictionary.length)];
    }

    @SuppressWarnings("MagicNumber")
    public static String getHangman(int fails) {
        return switch (fails) {
            case 0 -> """

                        ______
                       |      |
                       |
                       |
                       |
                    ___|___
                    """;
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

    public static boolean userInputIsCorrect(String userGuess) {
        return userGuess.matches("^[A-Za-z]$");
    }

    public static boolean isUserGuessInWord(String userGuess, String word) {
        return (word.contains(userGuess.toUpperCase()) || word.contains(userGuess.toLowerCase()));
    }

    public static String updateStarWord(String userGuess, String word, String starWord) {
        String[] letters = word.split("");
        String[] lettersInStarWord = starWord.split("");

        for (int i = 0; i < word.length(); i++) {
            if (userGuess.equalsIgnoreCase(letters[i])) {
                lettersInStarWord[i] = userGuess;
            }
        }
        return String.join("", lettersInStarWord);
    }

    public static void updateAvailableLetters(String userGuess, String[] letters) {
        for (int i = 0; i < letters.length; i++) {
            if (userGuess.equalsIgnoreCase(letters[i])) {
                letters[i] = "#";
                break;
            }
        }
    }

    public static boolean isLetterWasAlreadyChosen(String userGuess, String[] letters) {
        for (String letter : letters) {
            if (userGuess.equalsIgnoreCase(letter)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUserGaveUp(String userGuess) {
        return userGuess.equalsIgnoreCase("!STOP!");
    }
}
