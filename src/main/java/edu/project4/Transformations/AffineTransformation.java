package edu.project4.Transformations;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("MagicNumber")
public class AffineTransformation {

    public double bound = 1.5;

    public double a = ThreadLocalRandom.current().nextDouble(-bound, bound);

    public double b = ThreadLocalRandom.current().nextDouble(-bound, bound);

    public double c = ThreadLocalRandom.current().nextDouble(-bound, bound);

    public double d = ThreadLocalRandom.current().nextDouble(-bound, bound);

    public double e = ThreadLocalRandom.current().nextDouble(-bound, bound);

    public double f = ThreadLocalRandom.current().nextDouble(-bound, bound);

    public Color color = getRandomColor();

    public AffineTransformation() {
        while (!isValid(a, b, d, e)) {
            a = ThreadLocalRandom.current().nextDouble(-bound, bound);
            b = ThreadLocalRandom.current().nextDouble(-bound, bound);
            c = ThreadLocalRandom.current().nextDouble(-bound, bound);
            d = ThreadLocalRandom.current().nextDouble(-bound, bound);
            e = ThreadLocalRandom.current().nextDouble(-bound, bound);
            f = ThreadLocalRandom.current().nextDouble(-bound, bound);
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
