package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSearch extends RecursiveTask<List<Path>> {

    private final Path root;
    private final long minSize;
    private final long maxSize;
    private final String extension;

    public FileSearch(Path root, long minSize, long maxSize, String extension) {
        this.root = root;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.extension = extension;
    }

    @Override
    protected List<Path> compute() {

        // Получаем список всех файлов и директорий в корневой директории
        List<Path> rootContent = new ArrayList<>();
        try (Stream<Path> rootStream = Files.list(root)) {
            rootContent = rootStream.collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Cannot get list of files and subdirectories!");
        }

        // Сохраняем все файлы и субдиректории в списки
        List<Path> files = new ArrayList<>();
        List<Path> subdirectories = new ArrayList<>();
        for (Path content : rootContent) {
            if (Files.isRegularFile(content)) {
                files.add(content);
            } else if (Files.isDirectory(content)) {
                subdirectories.add(content);
            }
        }

        // Проверяем файлы на соответствие по размеру и расширению
        List<Path> targetFiles = new ArrayList<>();
        for (Path file : files) {
            if (isSizeAndExtensionValid(file)) {
                targetFiles.add(file);
            }
        }

        // Рекурсивно выполняем ту же задачу для каждой директории
        List<FileSearch> tasks = new ArrayList<>();
        for (Path subdirectory : subdirectories) {
            FileSearch task = new FileSearch(subdirectory, minSize, maxSize, extension);
            task.fork();
            tasks.add(task);
        }

        // Файлы, которые лежат в субдиректориях и являются подходящими, также подходят
        for (FileSearch task : tasks) {
            targetFiles.addAll(task.join());
        }

        return targetFiles;
    }

    private boolean isSizeAndExtensionValid(Path file) {
        long fileSize = 0;
        try {
            fileSize = Files.size(file);
        } catch (IOException e) {
            System.out.println("Cannot get file size! File: " + file);
        }
        return (fileSize >= minSize)
            && (fileSize <= maxSize)
            && (file.getFileName().toString().endsWith(extension));
    }
}
