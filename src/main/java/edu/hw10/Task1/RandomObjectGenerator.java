package edu.hw10.Task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static edu.hw10.Task1.fieldsGenerators.ByteGenerator.generateByte;
import static edu.hw10.Task1.fieldsGenerators.DoubleGenerator.generateDouble;
import static edu.hw10.Task1.fieldsGenerators.IntGenerator.generateInt;
import static edu.hw10.Task1.fieldsGenerators.StringGenerator.generateString;

@SuppressWarnings({"RegexpSinglelineJava", "ReturnCount"})
public class RandomObjectGenerator {

    public <T> T nextObject(Class<T> clazz) {
        try {
            Constructor<T> constructor = findConstructorWithAnnotations(clazz.getDeclaredConstructors());
            if (constructor != null) {
                Object[] args = generateArguments(constructor.getParameters());
                return constructor.newInstance(args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> T nextObject(Class<T> clazz, String factoryMethodName) {
        try {
            Method factoryMethod = findMethodWithAnnotations(clazz.getDeclaredMethods(), factoryMethodName);
            if (factoryMethod != null) {
                Object[] args = generateArguments(factoryMethod.getParameters());
                return (T) factoryMethod.invoke(null, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private @Nullable Constructor findConstructorWithAnnotations(Constructor @NotNull [] constructors) {
        for (Constructor constructor : constructors) {
            if (hasParameterAnnotations(constructor.getParameters())) {
                return constructor;
            }
        }
        return null;
    }

    private @Nullable Method findMethodWithAnnotations(Method @NotNull [] methods, String methodName) {
        for (Method method : methods) {
            if (method.getName().equals(methodName) && hasParameterAnnotations(method.getParameters())) {
                return method;
            }
        }
        return null;
    }

    private boolean hasParameterAnnotations(Parameter @NotNull [] parameters) {
        for (Parameter parameter : parameters) {
            if (parameter.getAnnotations().length > 0) {
                return true;
            }
        }
        return false;
    }

    private Object @NotNull [] generateArguments(Parameter @NotNull [] parameters) {
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> parameterType = parameters[i].getType();
            args[i] = generateValue(parameterType, parameters[i].getAnnotations());
        }
        return args;
    }

    // Сюда добавлять условия для добавления новых типов
    private @Nullable Object generateValue(Class<?> type, Annotation[] annotations) {
        if (type == byte.class || type == Byte.class) {
            return generateByte(annotations);
        } else if (type == int.class || type == Integer.class) {
            return generateInt(annotations);
        } else if (type == double.class || type == Double.class) {
            return generateDouble(annotations);
        } else if (type == String.class) {
            return generateString(annotations);
        }
        return null;
    }
}
