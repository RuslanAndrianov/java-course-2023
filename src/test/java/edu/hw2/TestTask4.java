package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask4 {

    Task4.CallingInfo func1() {
        return func2();
    }

    Task4.CallingInfo func2() {
        return Task4.callInfo();
    }

    @Test
    @DisplayName("Тест метода callingInfo")
    void testCallingInfo() {
        assertEquals("CallingInfo[className=edu.hw2.TestTask4, methodName=func2]", func1().toString());
    }
}
