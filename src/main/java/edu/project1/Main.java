package edu.project1;

import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Main {

    private final static Logger LOGGER = LogManager.getLogger();

    private static void showState() {
        LOGGER.info("If you want to give up, you can always enter !STOP!");
        LOGGER.info("Intended word: " + GameState.starWord);
        LOGGER.info("Available letters: " + String.join(" ", GameState.letters));
        LOGGER.info("Input a letter:");
    }

    public static void main(String[] args) {

        // подготовка игры
        GameState.intendedWord = ProcessingGame.getRandomWordFromDictionary(GameState.DICTIONARY);
        GameState.starWord = "*".repeat(GameState.intendedWord.length());
        String userGuess;
        Scanner input = new Scanner(System.in);
        LOGGER.info("Welcome to Hangman!");

        // игра
        while (!GameState.gameOver) {

            showState();
            userGuess = input.nextLine();

            // пока не будет правильного ввода, дальше не идем
            while (!ProcessingGame.userInputIsCorrect(userGuess)
                || ProcessingGame.isLetterWasAlreadyChosen(userGuess, GameState.letters)) {

                // пользователь сдался
                if (ProcessingGame.isUserGaveUp(userGuess)) {
                    break;
                }

                LOGGER.info("Incorrect input! Try again!");
                showState();
                userGuess = input.nextLine();
            }

            // пользователь сдался
            if (ProcessingGame.isUserGaveUp(userGuess)) {
                GameState.fails = GameState.MAX_FAILS;
                break;
            }

            // если пользователь угадал
            if (ProcessingGame.isUserGuessInWord(userGuess, GameState.intendedWord)) {

                LOGGER.info("Correct!");

                GameState.starWord = ProcessingGame.updateStarWord(userGuess,
                    GameState.intendedWord, GameState.starWord);

                if (Objects.equals(GameState.starWord, GameState.intendedWord)) {
                    GameState.gameOver = true;
                }
            // если пользователь не угадал
            } else {

                GameState.fails++;

                LOGGER.info("No such letter!");
                LOGGER.info(ProcessingGame.getHangman(GameState.fails));

                if (GameState.fails == GameState.MAX_FAILS) {
                    GameState.gameOver = true;
                }

            }

            // в любом случае обновить доступные пользователю буквы
            ProcessingGame.updateAvailableLetters(userGuess, GameState.letters);
        }

        // конец игры
        if (GameState.fails == GameState.MAX_FAILS) {
            LOGGER.info("You've lost!");
        }

        if (Objects.equals(GameState.starWord, GameState.intendedWord)) {
            LOGGER.info("You've won! Congratulations!");
        }

        LOGGER.info("The intended word is " + GameState.intendedWord);

        input.close();
    }
}
