package edu.project1;

@SuppressWarnings("HideUtilityClassConstructor")
public class GameState {
    public static final String[] DICTIONARY = new String[] {"encapsulation",
        "polymorphism", "inheritance", "abstraction"};

    public static final int MAX_FAILS = 6;

    public static String[] letters = new String[] {"a", "b", "c",
        "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
        "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static int fails = 0;

    public static String intendedWord = "";

    public static String starWord = "";

    public static boolean gameOver = false;

}
