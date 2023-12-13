package edu.hw10.TestTask2.TestClasses;

import java.util.Random;

public class AStringGeneratorImpl implements AStringGenerator {
    @Override
    public String generateString(int number) {
        return "a".repeat(number);
    }
}
