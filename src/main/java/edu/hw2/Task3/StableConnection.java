package edu.hw2.Task3;

import edu.hw2.Main;

public class StableConnection implements Connection {

    @Override
    public void execute(String command) {
        Main.LOGGER.info("Command " + command + " successfully executed");
    }

    @Override
    public void close() throws Exception {}
}
