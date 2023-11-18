package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task6.Task6.prettyPrint;
import static edu.hw6.Task6.Task6.scanPorts;

public class TestTask6 {

    @Test
    @DisplayName("Пример вывода")
    void testScanPorts() {
        prettyPrint(scanPorts());
    }
}
