package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadPICalculator {

    public SingleThreadPICalculator(int iterations) {
        this.iterations = iterations;
    }

    private final int iterations;

    private static final int FOUR = 4;

    private static final int NANOSEC_TO_MILLISEC = 1_000_000;

    public List<Double> calculatePI() {

        int totalCount = 0;
        int circleCount = 0;

        long startTime = System.nanoTime();

        for (int i = 0; i < iterations; i++) {
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;

            if (isInCircle(x, y)) {
                circleCount++;
            }
            totalCount++;
        }

        double pi = (FOUR * (double) circleCount / (double) totalCount);

        long endTime = System.nanoTime();

        double workTimeMilliSeconds = (double) ((endTime - startTime) / NANOSEC_TO_MILLISEC);

        List<Double> result = new ArrayList<>();
        result.add(pi);
        result.add(workTimeMilliSeconds);

        return result;
    }

    private static boolean isInCircle(double x, double y) {
        return x * x + y * y < 1;
    }
}
