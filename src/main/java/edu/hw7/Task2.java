package edu.hw7;

import java.math.BigInteger;
import java.util.stream.IntStream;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {

    public static BigInteger factorial(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("Cannot find factorial of negative integer!");
        }

        if (n < 2) {
            return BigInteger.valueOf(1);
        }

        return IntStream.rangeClosed(2, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger::multiply)
            .get();
    }
}
