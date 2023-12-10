package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("RegexpSinglelineJava")
public class DirectorySearch extends RecursiveTask<AbstractMap.SimpleEntry<List<Path>, Integer>> {

    private final Path root;
    private final int requiredFilesNumber;

    public DirectorySearch(Path root, int requiredFilesNumber) {
        this.root = root;
        this.requiredFilesNumber = requiredFilesNumber;
    }

    @Override
    protected AbstractMap.SimpleEntry<List<Path>, Integer> compute() {

        // Получаем список всех файлов и директорий в корневой директории
        List<Path> rootContent = new ArrayList<>();
        try (Stream<Path> rootStream = Files.list(root)) {
            rootContent = rootStream.collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Cannot get list of files and subdirectories!");
        }

        // Подсчет числа файлов и директорий в корневой директории
        int filesNumber = 0;
        List<Path> subdirectories = new ArrayList<>();
        for (Path content : rootContent) {
            if (Files.isRegularFile(content)) {
                filesNumber++;
            } else if (Files.isDirectory(content)) {
                subdirectories.add(content);
            }
        }

        // Рекурсивно выполняем ту же задачу для каждой директории
        List<DirectorySearch> tasks = new ArrayList<>();
        for (Path subdirectory : subdirectories) {
            DirectorySearch task = new DirectorySearch(subdirectory, requiredFilesNumber);
            task.fork();
            tasks.add(task);
        }

        // Файлы, которые лежат в субдиректории, лежат также и в корневой директории
        List<Path> targetDirectories = new ArrayList<>();
        for (DirectorySearch task : tasks) {
            var result = task.join();
            targetDirectories.addAll(result.getKey());
            filesNumber += result.getValue();
        }

        if (filesNumber > requiredFilesNumber) {
            targetDirectories.add(root);
        }

        return new AbstractMap.SimpleEntry<>(targetDirectories, filesNumber);
    }
}
