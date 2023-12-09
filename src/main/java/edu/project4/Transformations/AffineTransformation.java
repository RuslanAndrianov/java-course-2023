package edu.project4.Transformations;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation {

    public double BOUND = 1.5;

    public double a = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);

    public double b = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);

    public double c = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);

    public double d = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);

    public double e = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);

    public double f = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);

    public Color color = getRandomColor();

    public AffineTransformation() {
        while (!isValid(a, b, d, e)) {
            a = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
            b = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
            c = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
            d = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
            e = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
            f = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
            color = getRandomColor();
        }
    }

    private static boolean isValid(double a, double b, double d, double e) {
        return (a * a + d * d < 1)
            && (b * b + e * e < 1)
            && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }

    private static Color getRandomColor() {
        return new Color(
            ThreadLocalRandom.current().nextInt(0, 256),
            ThreadLocalRandom.current().nextInt(0, 256),
            ThreadLocalRandom.current().nextInt(0, 256)
        );
    }
}
