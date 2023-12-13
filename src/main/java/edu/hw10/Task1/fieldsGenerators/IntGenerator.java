package edu.hw10.Task1.fieldsGenerators;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import java.lang.annotation.Annotation;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class IntGenerator {
    public static int generateInt(Annotation @NotNull [] annotations) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (int) ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                max = (int) ((Max) annotation).value();
            }
        }

        return (int) (Math.random() * (max - min) + min);
    }
}
