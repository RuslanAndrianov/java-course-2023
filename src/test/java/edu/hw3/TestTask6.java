package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarketPQ;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestTask6 {

    @Test
    @DisplayName("Тест 1: стандартная очередь")
    void test1() {

        StockMarketPQ stockMarketPQ = new StockMarketPQ();

        stockMarketPQ.add(new Stock("Газпром", 100));
        stockMarketPQ.add(new Stock("Netflix", 1000));
        stockMarketPQ.add(new Stock("Apple", 900));

        assertEquals(stockMarketPQ.mostValuableStock().getCompanyName(), "Netflix");
        assertEquals(stockMarketPQ.mostValuableStock().getPrice(), 1000);
    }

    @Test
    @DisplayName("Тест 2: один элемент в очереди")
    void test2() {

        StockMarketPQ stockMarketPQ = new StockMarketPQ();

        stockMarketPQ.add(new Stock("Газпром", 100));

        assertEquals(stockMarketPQ.mostValuableStock().getCompanyName(), "Газпром");
        assertEquals(stockMarketPQ.mostValuableStock().getPrice(), 100);
    }

    @Test
    @DisplayName("Тест 3: несколько элементов с одной ценой")
    void test3() {

        StockMarketPQ stockMarketPQ = new StockMarketPQ();

        stockMarketPQ.add(new Stock("Газпром", 100));
        stockMarketPQ.add(new Stock("Netflix", 100));
        stockMarketPQ.add(new Stock("Apple", 100));

        assertEquals(stockMarketPQ.mostValuableStock().getCompanyName(), "Газпром");
        assertEquals(stockMarketPQ.mostValuableStock().getPrice(), 100);
    }

    @Test
    @DisplayName("Тест 4: пустая очередь")
    void test4() {

        StockMarketPQ stockMarketPQ = new StockMarketPQ();

        assertNull(stockMarketPQ.mostValuableStock());
    }
}
