package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import static java.nio.file.Files.exists;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void clonePath(@NotNull Path path) {
        int copyNum = 1;
        Path initFileName = path.getFileName();
        String[] fileNameParts = initFileName.toString().split("\\.");
        String oldName = fileNameParts[0];
        String fileExtension = fileNameParts[1];

        StringBuilder newName = new StringBuilder(fileNameParts[0]);
        Path newFilePath = path;

        while (exists(newFilePath)) {
            if (copyNum == 1) {
                newName.append(" — копия");
            } else {
                newName = new StringBuilder(oldName + " — копия (" + copyNum + ")");
            }
            copyNum++;
            newFilePath = Path.of(path.getParent() + "\\" + newName + "." + fileExtension);
        }

        try {
            Files.copy(path, newFilePath);
        } catch (IOException e) {
            LOGGER.info("Возникла ошибка во время копирования!");
        }
    }
}
