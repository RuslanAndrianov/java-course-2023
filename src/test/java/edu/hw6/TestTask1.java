package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask1 {

    @Test
    @DisplayName("Test")
    void testDiskMap() {
        DiskMap diskMap = new DiskMap(System.getProperty("user.dir") + "\\src\\main\\java\\edu\\hw6\\Task1\\map.txt");

        diskMap.put("key", "value");
        assertEquals(diskMap.getMemoryMap().size(), 1);

        diskMap.saveToFile();
        try {
            assertTrue(new ArrayList<>(List.of("key:value\r\n", "key:value\n"))
                .contains(Files.readString(Path.of(diskMap.getFilePath()))));
        } catch (Exception ignored) {}

        diskMap.clear();
        assertEquals(diskMap.getMemoryMap().size(), 0);

        diskMap.loadFromFile();
        assertEquals(diskMap.getMemoryMap().size(), 1);

        try {
            Files.delete(Path.of(diskMap.getFilePath()));
            Files.createFile(Path.of(diskMap.getFilePath()));
        } catch (Exception ignored) {}
    }
}
