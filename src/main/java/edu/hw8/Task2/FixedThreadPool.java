package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class FixedThreadPool implements ThreadPool {

    private final Thread[] threads;
    private final BlockingQueue<Runnable> queue;

    private FixedThreadPool(int numThreads) {
        threads = new Thread[numThreads];
        queue = new LinkedBlockingQueue<>();
    }

    @Contract("_ -> new") public static @NotNull FixedThreadPool create(int numThreads) {
        return new FixedThreadPool(numThreads);
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = queue.take();
                    task.run();
                } catch (InterruptedException ignored) {
                    // Поток был прерван, выполение прерывается
                    return;
                }
            }
        }
    }
}
