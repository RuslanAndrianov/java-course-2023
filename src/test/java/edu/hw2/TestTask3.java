package edu.hw2;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnection;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.StableConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask3 {

    @Test
    @DisplayName("Тест DefaultConnectionManager")
    void testDefaultConnectionManager() {
        ConnectionManager manager = new DefaultConnectionManager();
        int stableCount = 0;
        int faultyCount = 0;
        int otherCount = 0;

        for (int i = 0; i < 100; i++) {
            try (Connection connection = manager.getConnection()) {
                if (connection instanceof StableConnection) {
                   stableCount++;
                   continue;
                }
                if (connection instanceof FaultyConnection) {
                    faultyCount++;
                    continue;
                }
                otherCount++;
            }
            catch (Exception e) {
                Assertions.fail();
            }
        }

        Main.LOGGER.info("Stable: " + stableCount + "\nFaulty: " + faultyCount);

        assertEquals(0, otherCount);
        assertEquals(100, stableCount + faultyCount);
    }

    @Test
    @DisplayName("Тест FaultyConnection")
    void testFaultyConnection() {
        ConnectionManager manager = new FaultyConnectionManager();
        int executedCount = 0;
        int failCount = 0;

        for (int i = 0; i < 100; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute("Test");
                executedCount++;
            }
            catch (ConnectionException e) {
                failCount++;
            }
            catch (Exception e) {
                Assertions.fail();
            }
        }

        Main.LOGGER.info("Executed: " + executedCount + "\nFailed to execute: " + failCount);

        assertEquals(100, executedCount + failCount);
    }
}
