package edu.hw3;

import java.util.HashMap;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {

    public static <T> @NotNull HashMap<T, Integer> freqDict(@NotNull List<T> list) {
        HashMap<T, Integer> dict = new HashMap<>();
        for (T item: list) {
            if (dict.containsKey(item)) {
                dict.put(item, dict.get(item) + 1);
            } else {
                dict.put(item, 1);
            }
        }
        return dict;
    }
}
