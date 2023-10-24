package edu.hw2.Task3;

@SuppressWarnings("AvoidNoArgumentSuperConstructorCall")
public class ConnectionException extends RuntimeException {

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
