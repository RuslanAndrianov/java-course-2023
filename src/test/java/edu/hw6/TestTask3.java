package edu.hw6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static edu.hw6.Task3.Task3.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask3 {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("Тест из условия")
    void test1() {
        DirectoryStream.Filter<Path> filter = IS_REGULAR_FILE
            .and(IS_READABLE)
            .and(largerThan(50_000))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));

        Path dir = Path.of(System.getProperty("user.dir") + "\\src\\main\\java\\edu\\hw6\\Task3");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            for (Path entry : entries) {
                LOGGER.info(entry);
                assertEquals("PNG-example.png", entry.getFileName().toString());
            }
        } catch (IOException e) {
            LOGGER.info("Ошибка доступа к директории!");
        }
    }

    @Test
    @DisplayName("Тест остальных фильтров")
    void test2() {
        DirectoryStream.Filter<Path> filter = IS_WRITABLE
            .and(IS_EXECUTABLE)
            .or(lowerThan(100_000))
            .negate();

        Path dir = Path.of(System.getProperty("user.dir") + "\\src\\main\\java\\edu\\hw6\\Task3");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            for (Path entry : entries) {
                LOGGER.info(entry);
                assertEquals("PNG-example.png", entry.getFileName().toString());
            }
        } catch (IOException e) {
            LOGGER.info("Ошибка доступа к директории!");
        }
    }

    @Test
    @DisplayName("Фильтр ничего не нашел")
    void test3() {
        DirectoryStream.Filter<Path> filter = globMatches("*.gif");

        Path dir = Path.of(System.getProperty("user.dir") + "\\src\\main\\java\\edu\\hw6\\Task3");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            for (Path entry : entries) {
                LOGGER.info(entry);
                assertNull(entry.getFileName().toString());
            }
        } catch (IOException e) {
            LOGGER.info("Ошибка доступа к директории!");
        }
    }

    @Test
    @DisplayName("Несколько файлов подходят под фильтр")
    void test4() {
        DirectoryStream.Filter<Path> filter = regexContains("-");

        Path dir = Path.of(System.getProperty("user.dir") + "\\src\\main\\java\\edu\\hw6\\Task3");

        List<Path> files = new ArrayList<>();
        files.add(Path.of(System.getProperty("user.dir") + "\\src\\main\\java\\edu\\hw6\\Task3\\JPEG-example.jpg").getFileName());
        files.add(Path.of(System.getProperty("user.dir") + "\\src\\main\\java\\edu\\hw6\\Task3\\PNG-example.png").getFileName());

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            for (Path entry : entries) {
                LOGGER.info(entry);
                assertTrue(files.contains(entry.getFileName()));
            }
        } catch (IOException e) {
            LOGGER.info("Ошибка доступа к директории!");
        }
    }
}
