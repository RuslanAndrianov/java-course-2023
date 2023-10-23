package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Main {

    private final static Logger LOGGER = LogManager.getLogger();

    private static void gamePreparation() {
        GameState.intendedWord = ProcessingGame.getRandomWordFromDictionary(GameState.DICTIONARY);
        GameState.starWord = "*".repeat(GameState.intendedWord.length());
        LOGGER.info("Welcome to Hangman!");
    }

    private static void showGameState() {
        LOGGER.info("If you want to give up, you can always enter !STOP!");
        LOGGER.info("Intended word: " + GameState.starWord);
        LOGGER.info("Available letters: " + String.join(" ", GameState.letters));
        LOGGER.info("Input a letter:");
    }

    private static void handleCorrectGuess(String userGuess) {
        LOGGER.info("Correct!");

        GameState.starWord = ProcessingGame.updateStarWord(userGuess,
            GameState.intendedWord, GameState.starWord);

        if (GameState.starWord.equals(GameState.intendedWord)) {
            GameState.gameOver = true;
        }
    }

    private static void handleWrongGuess() {
        GameState.fails++;

        LOGGER.info("No such letter!");
        LOGGER.info(ProcessingGame.getHangman(GameState.fails));

        if (GameState.fails == GameState.MAX_FAILS) {
            GameState.gameOver = true;
        }
    }

    private static void gameProcess() {
        Scanner input = new Scanner(System.in);
        String userGuess;

        while (!GameState.gameOver) {

            showGameState();
            userGuess = input.nextLine();

            // пока не будет правильного ввода, дальше не идем
            while (!ProcessingGame.userInputIsCorrect(userGuess)
                || ProcessingGame.isLetterWasAlreadyChosen(userGuess, GameState.letters)) {

                LOGGER.info("Incorrect input! Try again!");
                showGameState();
                userGuess = input.nextLine();
            }

            // пользователь сдался
            if (ProcessingGame.isUserGaveUp(userGuess)) {
                GameState.gameOver = true;
                GameState.fails = GameState.MAX_FAILS;
                break;
            }

            // если пользователь угадал букву
            if (ProcessingGame.isUserGuessInWord(userGuess, GameState.intendedWord)) {
                handleCorrectGuess(userGuess);
            // если пользователь не угадал букву
            } else {
                handleWrongGuess();
            }

            // в любом случае обновить доступные пользователю буквы
            ProcessingGame.updateAvailableLetters(userGuess, GameState.letters);
        }

        input.close();
    }

    private static void showGameResult() {
        if (GameState.fails == GameState.MAX_FAILS) {
            LOGGER.info("You've lost!");
        }
        if (GameState.starWord.equals(GameState.intendedWord)) {
            LOGGER.info("You've won! Congratulations!");
        }
    }

    private static void showIntendedWord() {
        LOGGER.info("The intended word is " + GameState.intendedWord);
    }

    public static void main(String[] args) {
        // подготовка игры
        gamePreparation();

        // игра
        gameProcess();

        // конец игры
        showGameResult();
        showIntendedWord();
    }
}
