package edu.hw8.Task2;

@SuppressWarnings("HideUtilityClassConstructor")
public class Fibonacci {

    public static int getFibNum(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return getFibNum(n - 1) + getFibNum(n - 2);
    }
}
