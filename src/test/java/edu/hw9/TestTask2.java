package edu.hw9;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import static edu.hw9.Task2.Main.findDirectories;
import static edu.hw9.Task2.Main.findFiles;
import static java.nio.file.Files.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {

    String rootStr = System.getProperty("user.dir")
        + File.separator + "src"
        + File.separator + "test"
        + File.separator + "resources"
        + File.separator + "testRootDir";

//    Структура тестовой папки:
//
//    testRootDir
//    |
//    |--test1
//    |  |
//    |  |--test11
//    |  |  |
//    |  |  |--test111.txt (3 байта)
//    |  |
//    |  |--test11.txt (5 байт)
//    |  |--test12.txt (2 байта)
//    |
//    |--test2
//    |  |
//    |  |--test21.md (4 байта)
//    |  |--test22.txt (0 байт)
//    |
//    |--test3

    @BeforeEach
    void createTestDirectory() {
        try {
            createDirectory(Path.of(rootStr));
            createDirectory(Path.of(rootStr + File.separator + "test1"));
            createDirectory(Path.of(rootStr + File.separator + "test2"));
            createDirectory(Path.of(rootStr + File.separator + "test3"));
            createDirectory(Path.of(rootStr + File.separator + "test1" + File.separator + "test11"));
            createFile(Path.of(rootStr + File.separator + "test1" + File.separator + "test11.txt"));
            createFile(Path.of(rootStr + File.separator + "test1" + File.separator + "test12.txt"));
            createFile(Path.of(rootStr + File.separator + "test1" + File.separator + "test11" + File.separator + "test111.txt"));
            createFile(Path.of(rootStr + File.separator + "test2" + File.separator + "test21.md"));
            createFile(Path.of(rootStr + File.separator + "test2" + File.separator + "test22.txt"));
            try (FileWriter writer11 = new FileWriter(rootStr + File.separator + "test1" + File.separator + "test11.txt", true);
                 FileWriter writer12 = new FileWriter(rootStr + File.separator + "test1" + File.separator + "test12.txt", true);
                 FileWriter writer111 = new FileWriter(rootStr + File.separator + "test1" + File.separator + "test11" + File.separator + "test111.txt", true);
                 FileWriter writer21 = new FileWriter(rootStr + File.separator + "test2" + File.separator + "test21.md", true)) {
                writer11.write("abcde");
                writer12.write("ab");
                writer111.write("abc");
                writer21.write("abcd");
            } catch (IOException e) {
                System.out.println("Возникла ошибка во время записи, проверьте данные.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании тестовой директории!");
        }
    }

    @AfterEach
    void deleteTestDirectory() {
        try {
            delete(Path.of(rootStr + File.separator + "test2" + File.separator + "test22.txt"));
            delete(Path.of(rootStr + File.separator + "test2" + File.separator + "test21.md"));
            delete(Path.of(rootStr + File.separator + "test1" + File.separator + "test11" + File.separator + "test111.txt"));
            delete(Path.of(rootStr + File.separator + "test1" + File.separator + "test12.txt"));
            delete(Path.of(rootStr + File.separator + "test1" + File.separator + "test11.txt"));
            delete(Path.of(rootStr + File.separator + "test1" + File.separator + "test11"));
            delete(Path.of(rootStr + File.separator + "test3"));
            delete(Path.of(rootStr + File.separator + "test2"));
            delete(Path.of(rootStr + File.separator + "test1"));
            delete(Path.of(rootStr));
        } catch (IOException e) {
            System.out.println("Ошибка при удалении тестовой директории!");
        }
    }

    @Test
    @DisplayName("Тест DirectorySearch")
    void testDirectorySearch() {
        List<Path> directories1 = findDirectories(Path.of(rootStr), 2);
        assertEquals(directories1, List.of(Path.of(rootStr + File.separator + "test1"), Path.of(rootStr)));

        List<Path> directories2 = findDirectories(Path.of(rootStr), 100);
        assertEquals(directories2, List.of());
    }

    @Test
    @DisplayName("Тест FileSearch")
    void testFileSearch() {
        List<Path> files1 = findFiles(Path.of(rootStr), 3, 5, "txt");
        assertEquals(files1, List.of(
            Path.of(rootStr + File.separator + "test1" + File.separator + "test11.txt"),
            Path.of(rootStr + File.separator + "test1" + File.separator + "test11" + File.separator + "test111.txt")));

        List<Path> files2 = findFiles(Path.of(rootStr), 3, 5, "md");
        assertEquals(files2, List.of(Path.of(rootStr + File.separator + "test2" + File.separator + "test21.md")));

        List<Path> files3 = findFiles(Path.of(rootStr), 10, 20, "txt");
        assertEquals(files3, List.of());
    }
}
