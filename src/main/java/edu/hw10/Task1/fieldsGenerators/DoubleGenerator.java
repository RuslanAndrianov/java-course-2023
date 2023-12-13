package edu.hw10.Task1.fieldsGenerators;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import java.lang.annotation.Annotation;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class DoubleGenerator {
    public static double generateDouble(Annotation @NotNull [] annotations) {
        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                max = ((Max) annotation).value();
            }
        }

        return Math.random() * (max - min) + min;
    }
}
