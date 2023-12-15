package edu.hw11;

import edu.hw11.Task2.AnotherArithmeticUtils;
import edu.hw11.Task2.ArithmeticUtils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {

    @Test
    void test() throws InstantiationException, IllegalAccessException, NoSuchMethodException,
        InvocationTargetException {

        ArithmeticUtils au = new ArithmeticUtils();
        assertEquals(au.sum(2, 3), 5);

        int dynamicResult = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(AnotherArithmeticUtils.class))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded()
            .getDeclaredConstructor()
            .newInstance()
            .sum(2, 3);

        assertEquals(dynamicResult, 6);
    }
}
