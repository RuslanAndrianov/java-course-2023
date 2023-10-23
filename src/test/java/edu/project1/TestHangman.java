package edu.project1;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestHangman {

    boolean isWordInDictionary(String word, String @NotNull [] dictionary) {
        for (String el: dictionary) {
            if (word.equalsIgnoreCase(el)) {
                return true;
            }
        }
        return false;
    }

    @Test
    @DisplayName("Тест метода getRandomWordFromDictionary")
    void testGetRandomWordFromDictionary() {
        for (int i = 0; i < 20; i++) {
            assertTrue(isWordInDictionary(ProcessingGame.getRandomWordFromDictionary(GameState.DICTIONARY), GameState.DICTIONARY));
        }

        assertFalse(isWordInDictionary("apple", new String[] {"tomato", "cucumber", "cabbage"}));
    }

    @Test
    @DisplayName("Тест метода userInputIsCorrect")
    void testUserInputIsCorrect() {
        assertTrue(ProcessingGame.userInputIsCorrect("A"));
        assertTrue(ProcessingGame.userInputIsCorrect("a"));
        assertTrue(ProcessingGame.userInputIsCorrect("z"));
        assertTrue(ProcessingGame.userInputIsCorrect("Z"));
        assertTrue(ProcessingGame.userInputIsCorrect("!STOP!"));
        assertTrue(ProcessingGame.userInputIsCorrect("!stop!"));
        assertFalse(ProcessingGame.userInputIsCorrect("A B"));
        assertFalse(ProcessingGame.userInputIsCorrect("ab"));
        assertFalse(ProcessingGame.userInputIsCorrect("1"));
        assertFalse(ProcessingGame.userInputIsCorrect("["));
        assertFalse(ProcessingGame.userInputIsCorrect("a "));
        assertFalse(ProcessingGame.userInputIsCorrect("ю"));
    }

    @Test
    @DisplayName("Тест метода isLetterWasAlreadyChosen")
    void testIsLetterWasAlreadyChosen() {
        String[] letters1 = new String[] {"a", "b", "c"};
        String[] letters2 = new String[] {"a", "#", "c"};
        String[] letters3 = new String[] {"a", "#", "#"};

        assertTrue(ProcessingGame.isLetterWasAlreadyChosen("b", letters2));
        assertTrue(ProcessingGame.isLetterWasAlreadyChosen("b", letters3));
        assertTrue(ProcessingGame.isLetterWasAlreadyChosen("c", letters3));
        assertFalse(ProcessingGame.isLetterWasAlreadyChosen("a", letters1));
        assertFalse(ProcessingGame.isLetterWasAlreadyChosen("c", letters2));
        assertFalse(ProcessingGame.isLetterWasAlreadyChosen("a", letters3));
        // пользователь сдается
        assertFalse(ProcessingGame.isLetterWasAlreadyChosen("!STOP!", letters1));
        assertFalse(ProcessingGame.isLetterWasAlreadyChosen("!stop!", letters1));
    }

    @Test
    @DisplayName("Тест метода isUserGaveUp")
    void testIsUserGaveUp() {
        assertTrue(ProcessingGame.isUserGaveUp("!STOP!"));
        assertTrue(ProcessingGame.isUserGaveUp("!stop!"));
        assertFalse(ProcessingGame.isUserGaveUp("a"));
        assertFalse(ProcessingGame.isUserGaveUp("STOP"));
    }

    @Test
    @DisplayName("Тест метода isUserGuessInWord")
    void testIsUserGuessInWord() {
        assertTrue(ProcessingGame.isUserGuessInWord("A", "abcd"));
        assertTrue(ProcessingGame.isUserGuessInWord("a", "abcd"));
        assertTrue(ProcessingGame.isUserGuessInWord("d", "abcd"));
        assertFalse(ProcessingGame.isUserGuessInWord("X", "abcd"));
        assertFalse(ProcessingGame.isUserGuessInWord("Z", "abcd"));
        assertFalse(ProcessingGame.isUserGuessInWord("z", "abcd"));
    }

    @Test
    @DisplayName("Тест метода updateStarWord")
    void testUpdateStarWord() {
        assertEquals("*all*n", ProcessingGame.updateStarWord("a", "ballon","**ll*n"));
        assertEquals("z**", ProcessingGame.updateStarWord("Z", "zoo","***"));
        assertEquals("w*n**w", ProcessingGame.updateStarWord("W", "window","**n***"));
    }
}
