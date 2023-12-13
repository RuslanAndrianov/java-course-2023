package edu.hw10.TestTask2.TestClasses;

import edu.hw10.Task2.Cache;

public interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
