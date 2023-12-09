package edu.project4.Model;

public class Rect {

    public double XMIN;

    public double XMAX;

    public double YMIN;

    public double YMAX;

    public Rect(FractalImage image, String transformType) {

        // Экспериментально установленные коэффициенты для красивого вывода
        double k = switch (transformType) {
            case "heart" -> 2.5;
            case "disk", "sinus", "spherical" -> 0.5;
            case "polar" -> 0.25;
            default -> 1;
        };

        if (image.width > image.height) {
            XMIN = (double) -image.width / image.height * k;
            XMAX = -XMIN;
            YMIN = -k;
            YMAX = k;
        } else {
            XMIN = -k;
            XMAX = k;
            YMIN = (double) -image.height / image.width * k;
            YMAX = -YMIN;
        }
    }
}
