package edu.hw2.Task3;

@SuppressWarnings("MagicNumber")
public class FaultyConnection implements Connection {
    @Override
    public void execute(String command) {
        if (Math.random() > 0.95) {
            throw new ConnectionException();
        }
    }

    @Override
    public void close() throws Exception {}
}
