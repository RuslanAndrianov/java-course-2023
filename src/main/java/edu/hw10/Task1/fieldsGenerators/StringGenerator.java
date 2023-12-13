package edu.hw10.Task1.fieldsGenerators;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("HideUtilityClassConstructor")
public class StringGenerator {

    private static final int CHAR_A = 97;

    private static final int CHAR_Z = 122;

    private static final int DEFAULT_MAX_LENGTH = 11;

    public static @Nullable String generateString(Annotation @org.jetbrains.annotations.NotNull [] annotations) {
        int minLength = 1;
        int maxLength = DEFAULT_MAX_LENGTH;
        boolean isNotNull = false;
        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                isNotNull = true;
            } else if (annotation instanceof Min minAnnotation) {
                minLength = (int) minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                maxLength = (int) maxAnnotation.value();
            }
        }
        if (!isNotNull) {
            return null;
        }

        int leftLimit = CHAR_A;
        int rightLimit = CHAR_Z;
        int targetStringLength = (int) (Math.abs(maxLength - minLength)
            * Math.random() + Math.min(maxLength, minLength));
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
