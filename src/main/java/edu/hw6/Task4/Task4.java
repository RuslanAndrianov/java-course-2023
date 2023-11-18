package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {

    public static final String PHRASE = "Programming is learned by writing programs. ― Brian Kernighan";

    private static final Logger LOGGER = LogManager.getLogger();

    public static void streamComposition(Path path) {

        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                LOGGER.info("Ошибка при создании файла!");
            }
        }

        try (OutputStream os = Files.newOutputStream(path);
             CheckedOutputStream checkedOS = new CheckedOutputStream(os, new Adler32());
             BufferedOutputStream bufferedOS = new BufferedOutputStream(checkedOS);
             OutputStreamWriter osWriter = new OutputStreamWriter(bufferedOS, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(osWriter)) {
                printWriter.write(PHRASE);
        } catch (IOException e) {
            LOGGER.info("Возникла ошибка в цепочке стримов!");
        }

    }
}
