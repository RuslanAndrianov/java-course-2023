package edu.project4;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation {

    double BOUND = 1.5;

    double a = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
    double b = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
    double c = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
    double d = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
    double e = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
    double f = ThreadLocalRandom.current().nextDouble(-BOUND, BOUND);
    Color color = getRandomColor();

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

    public boolean isValid(double a, double b, double d, double e) {
        return (a * a + d * d < 1)
            && (b * b + e * e < 1)
            && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }

    public Color getRandomColor() {
        return new Color(
            ThreadLocalRandom.current().nextInt(0, 256),
            ThreadLocalRandom.current().nextInt(0, 256),
            ThreadLocalRandom.current().nextInt(0, 256)
        );
    }
}
