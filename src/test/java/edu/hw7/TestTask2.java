package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw7.Task2.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestTask2 {

    @Test
    @DisplayName("Тест метода нахождения факториала")
    void testFactorial() {

        try {
            factorial(-1);
        } catch (Exception e) {
            assertInstanceOf(IllegalArgumentException.class, e);
        }

        assertEquals(1, factorial(0).intValue());
        assertEquals(1, factorial(1).intValue());
        assertEquals(2, factorial(2).intValue());
        assertEquals(120, factorial(5).intValue());
        assertEquals(3628800, factorial(10).intValue());
    }
}
