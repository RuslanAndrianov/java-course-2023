package edu.hw2.Task3;

import edu.hw2.Main;

public class FaultyConnection implements Connection {

    private final static double SUCCESS_EXECUTE_CHANCE = 0.9;

    @Override
    public void execute(String command) throws ConnectionException {
        if (Math.random() > SUCCESS_EXECUTE_CHANCE) {
            Main.LOGGER.info("Execution of command " + command + " failed");
            throw new ConnectionException("Execution failed");
        }
        Main.LOGGER.info("Command " + command + " successfully executed");
    }

    @Override
    public void close() throws Exception {}
}
