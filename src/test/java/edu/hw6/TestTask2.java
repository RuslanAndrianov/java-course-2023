package edu.hw6;

import edu.hw6.Task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestTask2 {

    void createTestFile(String strPath) {
        try {
            FileWriter writer = new FileWriter(strPath, true);
            writer.write("Hello!");
            writer.close();
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи!");
        }
    }

    @Test
    @DisplayName("Test")
    void test() {
        String strPath = System.getProperty("user.dir") + "\\src\\main\\java\\edu\\hw6\\Task2\\test.txt";
        Path testFile = Path.of(strPath);
        if (!Files.exists(testFile)) {
            createTestFile(strPath);
        }
        Task2.clonePath(testFile);
    }
}
