package edu.hw10.TestTask2;

import edu.hw10.Task2.CacheProxy;
import edu.hw10.TestTask2.TestClasses.FibCalculator;
import edu.hw10.TestTask2.TestClasses.FibCalculatorImpl;
import edu.hw10.TestTask2.TestClasses.AStringGenerator;
import edu.hw10.TestTask2.TestClasses.AStringGeneratorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask2 {

    @Test
    @DisplayName("Тест FibCalculator")
    void testFibCalculator() {
        System.out.println("Тест FibCalculator");

        FibCalculator calculator = new FibCalculatorImpl();
        FibCalculator proxy = CacheProxy.create(calculator, FibCalculator.class);
        String cacheFileStrPath =  System.getProperty("user.dir") + "\\fib_10\\cache.txt";

        long result1 = proxy.fib(10);
        System.out.println("Результат: " + result1);

        assertTrue(Files.exists(Path.of(cacheFileStrPath)));

        try (BufferedReader reader = new BufferedReader(new FileReader(cacheFileStrPath))) {
            assertEquals(reader.readLine(), "55");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении из файла!");
        }

        long result2 = proxy.fib(10);
        System.out.println("Результат: " + result2);
        assertEquals(result2, 55);

        long result3 = proxy.fib(10);
        System.out.println("Результат: " + result3);
        assertEquals(result3, 55);
        System.out.println();
    }

    @Test
    @DisplayName("Тест AStringGenerator")
    void testAStringGenerator() {
        System.out.println("Тест AStringGenerator");

        AStringGenerator generator = new AStringGeneratorImpl();
        AStringGenerator proxy = CacheProxy.create(generator, AStringGenerator.class);
        String cacheFileStrPath = System.getProperty("user.dir") + "\\generateString_10\\cache.txt";

        String result1 = proxy.generateString(10);
        System.out.println("Результат: " + result1);

        assertFalse(Files.exists(Path.of(cacheFileStrPath)));

        boolean errorIsThrown = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(cacheFileStrPath))) {
            reader.readLine();
            errorIsThrown = false;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении из файла!");
            assertTrue(errorIsThrown);
        }
        assertTrue(errorIsThrown);

        String result2 = proxy.generateString(10);
        System.out.println("Результат: " + result2);
        assertEquals(result2, "aaaaaaaaaa");

        String result3 = proxy.generateString(10);
        System.out.println("Результат: " + result3);
        assertEquals(result3, "aaaaaaaaaa");
    }

}
