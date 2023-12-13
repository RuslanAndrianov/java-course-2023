package edu.hw10.TestTask2.TestClasses;

import edu.hw10.Task2.Cache;

public interface AStringGenerator {
    @Cache(persist = false)
    String generateString(int number);
}
