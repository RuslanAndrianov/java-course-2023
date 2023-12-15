package edu.hw11;

import edu.hw11.Task3.FibonacciImplementation;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask3 {

    @Test
    public void testTask3() throws Exception {
        Class<?> dynamicClass = new ByteBuddy()
            .subclass(Object.class)
            .name("MyGeneratedClass")
            .defineMethod("fib", long.class, Visibility.PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new FibonacciImplementation())
            .make()
            .load(TestTask3.class.getClassLoader())
            .getLoaded();

        Method fibMethod = dynamicClass.getDeclaredMethod("fib", int.class);
        long result = (long) fibMethod.invoke(dynamicClass.getDeclaredConstructor().newInstance(), 10);
        assertEquals(result, 55);
    }
}
