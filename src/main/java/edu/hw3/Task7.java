package edu.hw3;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.Comparator;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task7 {

    @Contract(pure = true) public static @NotNull Comparator<String> nullSafeComparator() {

        return (str1, str2) -> {
            if (str1 == null && str2 == null) {
                return 0;
            }
            if (str1 == null) {
                return -1;
            }
            if (str2 == null) {
                return 1;
            }
            return str1.compareTo(str2);
        };
    }
}
