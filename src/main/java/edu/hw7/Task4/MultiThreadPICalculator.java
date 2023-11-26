package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("RegexpSinglelineJava")
public class MultiThreadPICalculator {

    public MultiThreadPICalculator(int iterations, int threadsNumber) {
        this.iterations = iterations;
        this.threadsNumber = threadsNumber;
    }

    private final int iterations;

    private final int threadsNumber;

    private static final int FOUR = 4;

    private static final int NANOSEC_TO_MILLISEC = 1_000_000;

    public List<Double> calculatePI() {

        int circleCount = 0;
        long startTime = System.nanoTime();

        try (ExecutorService service = Executors.newFixedThreadPool(threadsNumber)) {

            int iterationsPerThread = iterations / threadsNumber;
            Future<Integer>[] futures = new Future[threadsNumber];

            for (int i = 0; i < threadsNumber; i++) {
                futures[i] = service.submit(() -> calculateCirclePoints(iterationsPerThread));
            }

            for (int i = 0; i < threadsNumber; i++) {
                circleCount += futures[i].get();
            }

            service.shutdown();

        } catch (CancellationException e) {
            System.out.println("Computation was cancelled!");
        } catch (ExecutionException e) {
            System.out.println("Computation threw an exception!");
        } catch (InterruptedException e) {
            System.out.println("Current thread was interrupted while waiting!");
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong number of threads!");
        } catch (Exception e) {
            System.out.println("Unknown problem!");
        }

        double pi = FOUR * (double) circleCount / (double) iterations;

        long endTime = System.nanoTime();

        double workTimeMilliSeconds = (double) ((endTime - startTime) / NANOSEC_TO_MILLISEC);

        List<Double> result = new ArrayList<>();
        result.add(pi);
        result.add(workTimeMilliSeconds);

        return result;
    }

    private static int calculateCirclePoints(int iterationsPerThread) {
        int circleCount = 0;
        for (int i = 0; i < iterationsPerThread; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-1, 1);
            double y = ThreadLocalRandom.current().nextDouble(-1, 1);

            if (isInCircle(x, y)) {
                circleCount++;
            }
        }
        return circleCount;
    }

    private static boolean isInCircle(double x, double y) {
        return x * x + y * y < 1;
    }
}
