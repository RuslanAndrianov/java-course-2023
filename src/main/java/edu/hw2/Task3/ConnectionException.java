package edu.hw2.Task3;

@SuppressWarnings("AvoidNoArgumentSuperConstructorCall")
public class ConnectionException extends RuntimeException {
    public ConnectionException() {
        super();
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
