package edu.hw2.Task3;

import edu.hw2.Main;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) {

        int attempts = 0;

        while (attempts < maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                attempts++;
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                if (attempts == maxAttempts) {
                    throw new ConnectionException("Max connection attempts", e);
                }
            } catch (Exception e) {
                Main.LOGGER.info(e);
            }
        }
    }
}
