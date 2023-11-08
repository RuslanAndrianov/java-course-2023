package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask8 {

    @Test
    @DisplayName("Тест из условия")
    void test() {

        List<Integer> input = List.of(1, 2, 3);
        List<Integer> result = new ArrayList<>();

        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(input);

        while (backwardIterator.hasNext()) {
            result.add(backwardIterator.next());
        }

        assertEquals(result, List.of(3, 2, 1));
    }
}
