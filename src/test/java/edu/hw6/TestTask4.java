package edu.hw6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import static edu.hw6.Task4.Task4.PHRASE;
import static edu.hw6.Task4.Task4.streamComposition;
import static java.nio.file.Files.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask4 {

    Path dir = Path.of(System.getProperty("user.dir") + "\\src\\main\\java\\edu\\hw6\\Task4");

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Файл не существует")
    void test1() {

        Path path = Path.of(dir + "\\test1.txt");
        if (exists(path)) {
            try {
                delete(path);
            } catch (IOException e) {
                LOGGER.info("Не удалось удалить файл!");
            }
        }
        assertTrue(notExists(path));

        streamComposition(path);

        try {
            assertTrue(exists(path));
            assertEquals(readString(path), PHRASE);
        } catch (IOException e) {
            LOGGER.info("Не удалось прочитать файл!");
        }

        try {
            delete(path);
        } catch (IOException e) {
            LOGGER.info("Не удалось удалить файл!");
        }
    }

    @Test
    @DisplayName("Файл существует")
    void test2() {

        Path path = Path.of(dir + "\\test2.txt");

        if (exists(path)) {
            try {
                delete(path);
            } catch (IOException e) {
                LOGGER.info("Не удалось удалить файл!");
            }
            try {
                createFile(path);
                assertEquals(readString(path), "");
            } catch (IOException e) {
                LOGGER.info("Не удалось создать файл!");
            }
        }
        assertTrue(exists(path));

        streamComposition(path);

        try {
            assertEquals(readString(path), PHRASE);
        } catch (IOException e) {
            LOGGER.info("Не удалось прочитать файл!");
        }

        try {
            delete(path);
            createFile(path);
        } catch (IOException e) {
            LOGGER.info("Не удалось удалить файл!");
        }
    }
}
