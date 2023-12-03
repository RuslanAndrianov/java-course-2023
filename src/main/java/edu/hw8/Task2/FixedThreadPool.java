package edu.hw8.Task2;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import static java.lang.Thread.currentThread;

public class FixedThreadPool implements ThreadPool {

    private final int threadsNumber;

    private final Thread[] threads;

    private final BlockingQueue<Runnable> tasks;

    private volatile boolean isShutdown;

    private FixedThreadPool(int threadsNumber) {
        this.threadsNumber = threadsNumber;
        this.threads = new Thread[threadsNumber];
        this.tasks = new LinkedBlockingQueue<>();
        this.isShutdown = false;
    }

    @Contract("_ -> new") public static @NotNull FixedThreadPool create(int threadsNumber) {
        return new FixedThreadPool(threadsNumber);
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsNumber; ++i) {
            threads[i] = createThread();
            threads[i].start();
        }
    }

    @Contract(" -> new") private @NotNull Thread createThread() {
        return new Thread(new Executor());
    }

    private class Executor implements Runnable {
        @Override
        public void run() {
            while (isAlive()) {
                try {
                    Runnable task = tasks.take();
                    task.run();
                } catch (InterruptedException e) {
                    currentThread().interrupt();
                }
            }
        }

        private boolean isAlive() {
            return !(currentThread().isInterrupted());
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isShutdown) {
            throw new IllegalStateException();
        }

        try {
            tasks.put(runnable);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        isShutdown = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
