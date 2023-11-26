package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task1 {

    public static final int CYCLE_ITERATIONS = 100000;

    public static final int THREADS_COUNT = 10;

    public static int unsafeCounter = 0;

    public static AtomicInteger safeCounter = new AtomicInteger(0);

    public static Runnable unsafeIncrementer = () -> {
        for (int i = 0; i < CYCLE_ITERATIONS / THREADS_COUNT; i++) {
            unsafeCounter++;
        }
    };

    public static Runnable safeIncrementer = () -> {
        for (int i = 0; i < CYCLE_ITERATIONS / THREADS_COUNT; i++) {
            safeCounter.getAndAdd(1);
        }
    };
}
