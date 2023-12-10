package edu.hw9.Task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static List<Path> findDirectories(Path root, int requiredFilesNumber) {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(new DirectorySearch(root, requiredFilesNumber)).getKey();
        }
    }

    public static List<Path> findFiles(Path root, long minSize, long maxSize, String extension) {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(
                new FileSearch(root, minSize, maxSize, extension)
            );
        }
    }

    public static void main(String[] args) {
        String strPath = "C:\\Users\\rusla\\Desktop\\Программирование\\Java\\java-course-2023";
        int number = 200;
        Path path = Path.of(strPath);
        List<Path> directories = findDirectories(path, number);
        for (var directory : directories) {
            System.out.println(directory);
        }
        System.out.println();
        List<Path> files = findFiles(path, 100, 10000, "java");
        for (var file : files) {
            System.out.println(file);
        }
    }
}
