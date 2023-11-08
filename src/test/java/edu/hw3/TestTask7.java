package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static edu.hw3.Task7.nullSafeComparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask7 {

    @Test
    @DisplayName("Тест на добавление null")
    void testAddNull() {

        TreeMap<String, String> tree = new TreeMap<>(nullSafeComparator());

        tree.put("Привет", "Мир");
        tree.put(null, "World");

        assertTrue(tree.containsKey(null));
        assertEquals(tree.get(null), "World");

    }
}
