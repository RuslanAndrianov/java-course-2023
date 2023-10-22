package edu.hw2.Task3;

@SuppressWarnings("MagicNumber")
public class DefaultConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() throws ConnectionException {
        if (Math.random() > 0.95) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
