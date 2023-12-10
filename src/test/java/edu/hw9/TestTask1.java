package edu.hw9;

import edu.hw9.Task1.Metric;
import edu.hw9.Task1.StatsCollector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import static java.lang.Double.NaN;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask1 {

    @Test
    @DisplayName("Тест из условия")
    void test1() {
        double ERROR = 10e-9; // из-за арифметики с плавающей запятой
        StatsCollector collector = new StatsCollector(5);
        collector.push("metric_name", new double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        List<Metric> result = new ArrayList<>();
        try {
            sleep(100);
            result = collector.stats();
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception!");
        }
        for (Metric metric : result) {
            assertEquals(metric.name(), "metric_name");
            assertTrue(Math.abs(metric.sum() - 6.95) < ERROR);
            assertTrue(Math.abs(metric.average() - 1.39) < ERROR);
            assertEquals(metric.max(), 5.1);
            assertEquals(metric.min(), 0.05);
        }
    }

    @Test
    @DisplayName("Тест нескольких метрик")
    void test2() {
        double ERROR = 10e-9; // из-за арифметики с плавающей запятой
        StatsCollector collector = new StatsCollector(2);
        Map<String, double[]> data = Map.of(
            "name1", new double[] {0, -1.3, 2.5},
            "name2", new double[] {0.1},
            "name3", new double[] {}
        );

        List<Metric> expected = List.of(
            new Metric("name1", 1.2, 0.4, 2.5, -1.3),
            new Metric("name2", 0.1, 0.1, 0.1, 0.1),
            new Metric("name3", 0, NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
        );

        Thread[] threads = new Thread[data.size()];
        var latch = new CountDownLatch(data.size());
        int i = 0;
        for (Map.Entry<String, double[]> entry : data.entrySet()) {
            threads[i] = new Thread(() -> {

                latch.countDown();

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted exception! Cannot do latch.await()");
                }

                collector.push(entry.getKey(), entry.getValue());

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted exception! Cannot do sleep(100)");
                }
            });

            threads[i].start();
            i++;
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception! Cannot do thread.join()");
            }
        }

        List<Metric> result = collector.stats();

        for (Metric metric : result) {

             Metric expectation = expected.stream()
                .filter(e -> e.name().equals(metric.name()))
                .findFirst()
                .orElse(null);

            assert expectation != null;
            assertEquals(metric.name(), expectation.name());
            assertTrue(Math.abs(metric.sum() - expectation.sum()) < ERROR);
            assertEquals(metric.max(), expectation.max());
            assertEquals(metric.min(), expectation.min());
            if (Double.isNaN(metric.average())) {
                assertEquals(metric.name(), "name3");
                continue;
            }
            assertTrue(Math.abs(metric.average() - expectation.average()) < ERROR);
        }
    }
}
