package edu.hw4;

public class ValidationError extends Exception {

    public ValidationError(String message) {
        this.message = message;
    }

    public final String message;
}
