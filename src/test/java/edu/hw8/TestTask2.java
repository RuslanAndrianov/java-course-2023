package edu.hw8;

import edu.hw8.Task2.Fibonacci;
import edu.hw8.Task2.FixedThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import static edu.hw8.Task2.Fibonacci.getFibNum;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {

    @Test
    @DisplayName("Пример расчета")
    void testFibonacci() {
        int threadNumber = 10;
        FixedThreadPool threadPool = FixedThreadPool.create(threadNumber);
        threadPool.start();
        final List<Integer> result = new CopyOnWriteArrayList<>();
        for (int i = 0; i <= threadNumber; i++) {
            final int cur = i;
            threadPool.execute(() -> {
                result.add(getFibNum(cur));
            });
        }
        threadPool.close();
        assertEquals(result, List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55));
    }
}
