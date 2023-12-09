package edu.project4;

public class NonlinearTransormations {

    public static Point sinusoidal(double x, double y) {
        return new Point(Math.sin(x), Math.sin(y));
    }

    public static Point spherical(double x, double y) {
        double r2 = x * x + y * y;
        return new Point(x/r2, y/r2);
    }

    public static Point polar(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double theta = y/x;
        return new Point(Math.atan(theta)/Math.PI, r - 1);
    }

    public static Point heart(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double theta = y/x;
        return new Point(r * Math.sin(r * Math.atan(theta)), -r * Math.cos(r * Math.atan(theta)));
    }

    public static Point disk(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double theta = y/x;
        return new Point(1/Math.PI * Math.atan(theta) * Math.sin(Math.PI * r),
            1/Math.PI * Math.atan(theta) * Math.cos(Math.PI * r));
    }
}
