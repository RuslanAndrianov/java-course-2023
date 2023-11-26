package edu.hw7;

import edu.hw7.Task4.MultiThreadPICalculator;
import edu.hw7.Task4.SingleThreadPICalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;


// Все проверки занимают примерно 5 мин
public class TestTask4 {

    List<Double> singleThreadHelpFunction(int iterations, int cycleIterations) {

        SingleThreadPICalculator calculator = new SingleThreadPICalculator(iterations);
        double avgWorkTime = 0;
        double maxRelError = 0;
        double worstPI = 0;

        for (int i = 0; i < cycleIterations; i++) {

            List<Double> result = calculator.calculatePI();

            double PI = result.get(0);
            double workTime = result.get(1);
            double relError = Math.abs(Math.PI - PI)/PI;

            if (relError > maxRelError) {
                maxRelError = relError;
                worstPI = PI;
            }
            avgWorkTime += workTime;
        }
        avgWorkTime /= cycleIterations;
        maxRelError *= 100;

        List<Double> result = new ArrayList<>();
        result.add(avgWorkTime);
        result.add(maxRelError);

        System.out.println(iterations + " итераций");
        System.out.println("Среднее время работы: " + avgWorkTime + " мс");
        System.out.println("Худшее из значений π: " + worstPI);
        System.out.format("Максимальная относительная ошибка: %.4f", maxRelError);
        System.out.println("%\n");

        return result;
    }

    List<Double> multiThreadHelpFunction(
        int iterations, int threadsNumber, int cycleIterations) {

        MultiThreadPICalculator calculator = new MultiThreadPICalculator(iterations, threadsNumber);
        double avgWorkTime = 0;
        double maxRelError = 0;
        double worstPI = 0;

        for (int i = 0; i < cycleIterations; i++) {

            List<Double> result = calculator.calculatePI();

            double PI = result.get(0);
            double workTime = result.get(1);
            double relError = Math.abs(Math.PI - PI)/PI;

            if (relError > maxRelError) {
                maxRelError = relError;
                worstPI = PI;
            }
            avgWorkTime += workTime;
        }
        avgWorkTime /= cycleIterations;
        maxRelError *= 100;

        List<Double> result = new ArrayList<>();
        result.add(avgWorkTime);
        result.add(maxRelError);

        if (threadsNumber >= 2 && threadsNumber <= 4) {
            System.out.println(threadsNumber + " потока");
        } else {
            System.out.println(threadsNumber + " потоков");
        }
        System.out.println(iterations + " итераций");
        System.out.println("Среднее время работы: " + avgWorkTime + " мс");
        System.out.println("Худшее из значений π: " + worstPI);
        System.out.format("Максимальная относительная ошибка: %.4f", maxRelError);
        System.out.println("%\n");

        return result;
    }

    @Test
    @DisplayName("Тест однопоточного метода")
    void testSingleThread() {
        System.out.println("Тест однопоточного метода\n");

        // Примерное время исполнения: 1 с
        List<Double> result1e6 =
            singleThreadHelpFunction(1_000_000, 20);
        double avgWorkTime1e6 = result1e6.get(0);
        double maxRelError1e6 = result1e6.get(1);

        // Примерное время исполнения: 10 с
        List<Double> result1e7 =
            singleThreadHelpFunction(10_000_000, 20);
        double avgWorkTime1e7 = result1e7.get(0);
        double maxRelError1e7 = result1e7.get(1);

        // Примерное время исполнения: 20 c
        List<Double> result1e8 =
            singleThreadHelpFunction(100_000_000, 5);
        double avgWorkTime1e8 = result1e8.get(0);
        double maxRelError1e8 = result1e8.get(1);

        // Примерное время исполнения: 35 с
        List<Double> result1e9 =
            singleThreadHelpFunction(1_000_000_000, 1);
        double avgWorkTime1e9 = result1e9.get(0);
        double maxRelError1e9 = result1e9.get(1);

        // Сложность O(n):
        // При увеличении кол-ва итераций в 10 раз
        // время увеличивается примерно в 10 раз
        assertTrue(avgWorkTime1e7 > avgWorkTime1e6);
        assertTrue(avgWorkTime1e8 > avgWorkTime1e7);
        assertTrue(avgWorkTime1e9 > avgWorkTime1e8);

        // При увеличении кол-ва итераций точность возрастает
        assertTrue(maxRelError1e6 > maxRelError1e7);
        assertTrue(maxRelError1e7 > maxRelError1e8);
        assertTrue(maxRelError1e8 > maxRelError1e9);
    }

    @Test
    @DisplayName("Тест многопоточного метода: проверка зависимости от числа итераций," +
        "число потоков = const")
    void testMultiThreadIterations() {
        System.out.println("""
                Тест многопоточного метода:
                Проверка зависимости от числа итераций
                Число потоков = const
                """);

        // Примерное время исполнения: 0.5 с
        List<Double> result1e6 =
            multiThreadHelpFunction(1_000_000, 10, 100);
        double avgWorkTime1e6 = result1e6.get(0);
        double maxRelError1e6 = result1e6.get(1);

        // Примерное время исполнения: 2 с
        List<Double> result1e7 =
            multiThreadHelpFunction(10_000_000, 10, 100);
        double avgWorkTime1e7 = result1e7.get(0);
        double maxRelError1e7 = result1e7.get(1);

        // Примерное время исполнения: 3 c
        List<Double> result1e8 =
            multiThreadHelpFunction(100_000_000, 10, 20);
        double avgWorkTime1e8 = result1e8.get(0);
        double maxRelError1e8 = result1e8.get(1);

        // Примерное время исполнения: 15 с
        List<Double> result1e9 =
            multiThreadHelpFunction(1_000_000_000, 10, 10);
        double avgWorkTime1e9 = result1e9.get(0);
        double maxRelError1e9 = result1e9.get(1);

        // Сложность O(n):
        // При увеличении кол-ва итераций в 10 раз
        // время увеличивается примерно в 10 раз
        // Несколько потоков приводят к большему ускорению
        // по сравнению с однопоточной программой на небольшом числе итераций,
        // при большом кол-ве эффект теряется
        // (при переходе от 10^6 к 10^7 время увеличивается примерно в 5 раз,
        // от 10^7 к 10^8 - время увеличивается примерно в 7 раз,
        // от 10^8 к 10^9 - время увеличивается примерно в 9 раз)
        assertTrue(avgWorkTime1e7 / avgWorkTime1e6 <= 10);
        assertTrue(avgWorkTime1e8 / avgWorkTime1e7 <= 10);
        assertTrue(avgWorkTime1e9 / avgWorkTime1e8 <= 10);

        // При увеличении кол-ва итераций точность возрастает
        assertTrue(maxRelError1e6 > maxRelError1e7);
        assertTrue(maxRelError1e7 > maxRelError1e8);
        assertTrue(maxRelError1e8 > maxRelError1e9);
    }

    @Test
    @DisplayName("Тест многопоточного метода: проверка зависимости от числа потоков," +
        "число итераций = const")
    void testMultiThreadThreads() {
        System.out.println("""
                Тест многопоточного метода:
                Проверка зависимости от числа потоков
                Число итераций = const
                """);

        List<Double> result2 =
            multiThreadHelpFunction(100_000_000, 2,20);
        double avgWorkTime2 = result2.get(0);
        double maxRelError2 = result2.get(1);

        List<Double> result4 =
            multiThreadHelpFunction(100_000_000, 4,20);
        double avgWorkTime4 = result4.get(0);
        double maxRelError4 = result4.get(1);

        List<Double> result6 =
            multiThreadHelpFunction(100_000_000, 6,20);
        double avgWorkTime6 = result6.get(0);
        double maxRelError6 = result6.get(1);

        List<Double> result8 =
            multiThreadHelpFunction(100_000_000, 8,20);
        double avgWorkTime8 = result8.get(0);
        double maxRelError8 = result8.get(1);

        List<Double> result10 =
            multiThreadHelpFunction(100_000_000, 10,10);
        double maxRelError10 = result10.get(1);

        List<Double> result15 =
            multiThreadHelpFunction(100_000_000, 15,10);
        double maxRelError15 = result15.get(1);

        List<Double> result20 =
            multiThreadHelpFunction(100_000_000, 20,10);
        double maxRelError20 = result20.get(1);

        List<Double> result25 =
            multiThreadHelpFunction(100_000_000, 25,10);
        double maxRelError25 = result25.get(1);

        List<Double> result30 =
            multiThreadHelpFunction(100_000_000, 30,10);
        double maxRelError30 = result30.get(1);

        List<Double> result40 =
            multiThreadHelpFunction(100_000_000, 40,10);
        double maxRelError40 = result40.get(1);

        List<Double> result50 =
            multiThreadHelpFunction(100_000_000, 50,10);
        double maxRelError50 = result50.get(1);

        List<Double> result75 =
            multiThreadHelpFunction(100_000_000, 75,10);
        double maxRelError75 = result75.get(1);

        List<Double> result100 =
            multiThreadHelpFunction(100_000_000, 100,10);
        double maxRelError100 = result100.get(1);

        List<Double> result200 =
            multiThreadHelpFunction(100_000_000, 200,10);
        double avgWorkTime200 = result200.get(0);
        double maxRelError200 = result200.get(1);

        List<Double> result500 =
            multiThreadHelpFunction(100_000_000, 500,10);
        double avgWorkTime500 = result500.get(0);
        double maxRelError500 = result500.get(1);

        List<Double> result1000 =
            multiThreadHelpFunction(100_000_000, 1000,10);
        double avgWorkTime1000 = result1000.get(0);
        double maxRelError1000 = result1000.get(1);

        List<Double> result2000 =
            multiThreadHelpFunction(100_000_000, 2000,10);
        double avgWorkTime2000 = result2000.get(0);
        double maxRelError2000 = result2000.get(1);

        List<Double> result5000 =
            multiThreadHelpFunction(100_000_000, 5000,10);
        double avgWorkTime5000 = result5000.get(0);
        double maxRelError5000 = result5000.get(1);

        List<Double> result10000 =
            multiThreadHelpFunction(100_000_000, 10000,10);
        double avgWorkTime10000 = result10000.get(0);
        double maxRelError10000 = result10000.get(1);

        // Зависимость от числа потоков немонотонная:
        // сначала происходит увеличение производительности
        // (от 2 до 8 потоков),
        // затем производительность практически постоянна
        // (от 10 до 200 потоков),
        // после (от 500 до 10000 потоков) производительность ухудшается
        // из-за траты времени на переключение между большим числом потоков
        assertTrue(avgWorkTime2 > avgWorkTime4);
        assertTrue(avgWorkTime4 > avgWorkTime6);
        assertTrue(avgWorkTime6 > avgWorkTime8);
        assertTrue(avgWorkTime200 < avgWorkTime500);
        assertTrue(avgWorkTime500 < avgWorkTime1000);
        assertTrue(avgWorkTime1000 < avgWorkTime2000);
        assertTrue(avgWorkTime2000 < avgWorkTime5000);
        assertTrue(avgWorkTime5000 < avgWorkTime10000);

        // Относительная ошибка не зависит от числа потоков
        assertTrue(Math.abs(maxRelError2 - maxRelError4) < 0.01);
        assertTrue(Math.abs(maxRelError4 - maxRelError6) < 0.01);
        assertTrue(Math.abs(maxRelError6 - maxRelError8) < 0.01);
        assertTrue(Math.abs(maxRelError8 - maxRelError10) < 0.01);
        assertTrue(Math.abs(maxRelError10 - maxRelError15) < 0.01);
        assertTrue(Math.abs(maxRelError15 - maxRelError20) < 0.01);
        assertTrue(Math.abs(maxRelError20 - maxRelError25) < 0.01);
        assertTrue(Math.abs(maxRelError25 - maxRelError30) < 0.01);
        assertTrue(Math.abs(maxRelError30 - maxRelError40) < 0.01);
        assertTrue(Math.abs(maxRelError40 - maxRelError50) < 0.01);
        assertTrue(Math.abs(maxRelError50 - maxRelError75) < 0.01);
        assertTrue(Math.abs(maxRelError75 - maxRelError100) < 0.01);
        assertTrue(Math.abs(maxRelError100 - maxRelError200) < 0.01);
        assertTrue(Math.abs(maxRelError200 - maxRelError500) < 0.01);
        assertTrue(Math.abs(maxRelError500 - maxRelError1000) < 0.01);
        assertTrue(Math.abs(maxRelError1000 - maxRelError2000) < 0.01);
        assertTrue(Math.abs(maxRelError2000 - maxRelError5000) < 0.01);
        assertTrue(Math.abs(maxRelError5000 - maxRelError10000) < 0.01);
    }

    @Test
    @DisplayName("Однопоточный метод VS многопоточный метод")
    void testSingleVSMulti() {
        System.out.println("Однопоточный метод VS многопоточный метод");

        System.out.println("1 поток");
        List<Double> result1_1e6 =
            singleThreadHelpFunction(1_000_000, 20);
        double avgWorkTime1_1e6 = result1_1e6.get(0);
        double maxRelError1_1e6 = result1_1e6.get(1);

        List<Double> result10_1e6 =
            multiThreadHelpFunction(1_000_000, 10,20);
        double avgWorkTime10_1e6 = result10_1e6.get(0);
        double maxRelError10_1e6 = result10_1e6.get(1);

        System.out.println("1 поток");
        List<Double> result1_1e7 =
            singleThreadHelpFunction(10_000_000, 20);
        double avgWorkTime1_1e7 = result1_1e7.get(0);
        double maxRelError1_1e7 = result1_1e7.get(1);

        List<Double> result10_1e7 =
            multiThreadHelpFunction(10_000_000, 10,20);
        double avgWorkTime10_1e7 = result10_1e7.get(0);
        double maxRelError10_1e7 = result10_1e7.get(1);

        System.out.println("1 поток");
        List<Double> result1_1e8 =
            singleThreadHelpFunction(100_000_000, 10);
        double avgWorkTime1_1e8 = result1_1e8.get(0);
        double maxRelError1_1e8 = result1_1e8.get(1);

        List<Double> result10_1e8 =
            multiThreadHelpFunction(100_000_000, 10,20);
        double avgWorkTime10_1e8 = result10_1e8.get(0);
        double maxRelError10_1e8 = result10_1e8.get(1);

        System.out.println("1 поток");
        List<Double> result1_1e9 =
            singleThreadHelpFunction(1_000_000_000, 1);
        double avgWorkTime1_1e9 = result1_1e9.get(0);
        double maxRelError1_1e9 = result1_1e9.get(1);

        List<Double> result10_1e9 =
            multiThreadHelpFunction(1_000_000_000, 10,10);
        double avgWorkTime10_1e9 = result10_1e9.get(0);
        double maxRelError10_1e9 = result10_1e9.get(1);

        // Многопоточный метод при прочих равных условиях быстрее однопоточного
        assertTrue(avgWorkTime1_1e6 > avgWorkTime10_1e6);
        assertTrue(avgWorkTime1_1e7 > avgWorkTime10_1e7);
        assertTrue(avgWorkTime1_1e8 > avgWorkTime10_1e8);
        assertTrue(avgWorkTime1_1e9 > avgWorkTime10_1e9);

        // Относительная ошибка не зависит от числа потоков
        assertTrue(maxRelError1_1e6 / maxRelError10_1e6 <= 2);
        assertTrue(maxRelError1_1e7 / maxRelError10_1e7 <= 2);
        assertTrue(maxRelError1_1e8 / maxRelError10_1e8 <= 2);
        assertTrue(maxRelError1_1e9 / maxRelError10_1e9 <= 2);
    }
}
