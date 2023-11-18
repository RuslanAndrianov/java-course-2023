package edu.hw6.Task3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {

    public static final AbstractFilter IS_REGULAR_FILE = Files::isRegularFile;

    public static final AbstractFilter IS_READABLE = Files::isReadable;

    public static final AbstractFilter IS_WRITABLE = Files::isWritable;

    public static final AbstractFilter IS_EXECUTABLE = Files::isExecutable;

    @Contract(pure = true) public static @NotNull AbstractFilter largerThan(long size) {
        return (Path path) -> (Files.size(path) > size);
    }

    @Contract(pure = true) public static @NotNull AbstractFilter lowerThan(long size) {
        return (Path path) -> (Files.size(path) < size);
    }

    @Contract(pure = true) public static @NotNull AbstractFilter globMatches(String glob) {
        return (Path path) -> path.getFileSystem()
            .getPathMatcher("glob:**/*" + glob)
            .matches(path);
    }

    @Contract(pure = true) public static @NotNull AbstractFilter regexContains(String regex) {
        return (Path path) -> {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(path.getFileName().toString()).find();
        };
    }

    @Contract(pure = true) public static @NotNull AbstractFilter magicNumber(int... magicBytes) {
        return (Path path) -> {
            byte[] fileBytes = Files.readAllBytes(path);
            int[] unsignedFileBytes = byteToUnsignedByte(fileBytes);
            boolean[] flags = new boolean[magicBytes.length];

            for (int i = 0; i < magicBytes.length; i++) {
                for (int j = 0; j < magicBytes.length; j++) {
                    if (unsignedFileBytes[i] == magicBytes[j]) {
                        flags[j] = true;
                    }
                }
            }

            boolean result = true;
            for (boolean flag : flags) {
                result &= flag;
            }
            return result;
        };
    }

    @Contract(pure = true) private static int @NotNull [] byteToUnsignedByte(byte @NotNull [] bytes) {
        int[] unsignedBytes = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            unsignedBytes[i] = Byte.toUnsignedInt(bytes[i]);
        }
        return unsignedBytes;
    }
}
