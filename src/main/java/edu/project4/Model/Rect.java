package edu.project4.Model;

@SuppressWarnings("MagicNumber")
public class Rect {

    public double xMin;

    public double xMax;

    public double yMin;

    public double yMax;

    public Rect(FractalImage image, String transformType) {

        // Экспериментально установленные коэффициенты для красивого вывода
        double k = switch (transformType) {
            case "heart" -> 2.5;
            case "disk", "sinus", "spherical" -> 0.5;
            case "polar" -> 0.25;
            default -> 1;
        };

        if (image.width > image.height) {
            xMin = (double) -image.width / image.height * k;
            xMax = -xMin;
            yMin = -k;
            yMax = k;
        } else {
            xMin = -k;
            xMax = k;
            yMin = (double) -image.height / image.width * k;
            yMax = -yMin;
        }
    }
}
