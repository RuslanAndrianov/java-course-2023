package edu.hw10.Task1.fieldsGenerators;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import java.lang.annotation.Annotation;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class ByteGenerator {
    public static byte generateByte(Annotation @NotNull [] annotations) {
        byte min = Byte.MIN_VALUE;
        byte max = Byte.MAX_VALUE;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (byte) ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                max = (byte) ((Max) annotation).value();
            }
        }

        return (byte) (Math.random() * (max - min) + min);
    }
}
