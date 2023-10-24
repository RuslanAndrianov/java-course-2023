package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {

    private final static double STABLE_CONNECTION_CHANCE = 0.9;

    @Override
    public Connection getConnection() throws ConnectionException {
        if (Math.random() > STABLE_CONNECTION_CHANCE) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
