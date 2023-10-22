package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                if (connection instanceof StableConnection) {
                    connection.execute(command);
                    return;
                } else {
                    throw new ConnectionException();
                }
            } catch (ConnectionException e) {
                if (i == maxAttempts - 1) {
                    throw new ConnectionException("Max connection attempts", e);
                }
            } catch (Exception e) {
                LOGGER.info(e);
            }
        }
    }
}
