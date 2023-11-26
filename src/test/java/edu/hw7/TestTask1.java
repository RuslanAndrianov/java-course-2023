package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static edu.hw7.Task1.CYCLE_ITERATIONS;
import static edu.hw7.Task1.THREADS_COUNT;
import static edu.hw7.Task1.unsafeCounter;
import static edu.hw7.Task1.unsafeIncrementer;
import static edu.hw7.Task1.safeCounter;
import static edu.hw7.Task1.safeIncrementer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask1 {

    @Test
    @DisplayName("Тест ненадежного инкрементора")
    void testUnsafeIncrementer() {

        List<Thread> notSafeThreads = new ArrayList<>();

        for (int i = 0; i < THREADS_COUNT; i++) {
            notSafeThreads.add(new Thread(unsafeIncrementer));
        }

        for (int i = 0; i < THREADS_COUNT; i++) {
            notSafeThreads.get(i).start();
        }

        try {
            for (int i = 0; i < THREADS_COUNT; i++) {
                notSafeThreads.get(i).join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception!");
        }

        System.out.println("Unsafe counter: " + unsafeCounter);
        assertTrue(unsafeCounter <= CYCLE_ITERATIONS);
    }

    @Test
    @DisplayName("Тест надежного инкрементора")
    void testSafeIncrementer() {

        List<Thread> safeThreads = new ArrayList<>();

        for (int i = 0; i < THREADS_COUNT; i++) {
            safeThreads.add(new Thread(safeIncrementer));
        }

        for (int i = 0; i < THREADS_COUNT; i++) {
            safeThreads.get(i).start();
        }

        try {
            for (int i = 0; i < THREADS_COUNT; i++) {
                safeThreads.get(i).join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception!");
        }

        System.out.println("Safe counter: " + safeCounter);
        assertEquals(CYCLE_ITERATIONS, safeCounter.get());
    }
}
