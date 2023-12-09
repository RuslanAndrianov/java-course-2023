package edu.project4.Renderers;

import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import edu.project4.Model.Point;
import edu.project4.Model.Rect;
import edu.project4.Transformations.AffineTransformation;
import edu.project4.Transformations.NonlinearTransormations;
import org.jetbrains.annotations.NotNull;

public interface Renderer {

    int START_STEPS = -20;

    default void render(
        FractalImage image,
        @NotNull String transformType,
        int affCount,
        int samples,
        int iterPerSample,
        int symmetryAxes
    ) {
    }

    default AffineTransformation[] generateAffineTransformations(int affCount) {
        AffineTransformation[] affineTransformations = new AffineTransformation[affCount];
        for (int i = 0; i < affCount; i++) {
            affineTransformations[i] = new AffineTransformation();
        }
        return affineTransformations;
    }

    default Point nonlinearTransform(String transformType, double x, double y) {
        return switch (transformType) {
            case "heart" -> NonlinearTransormations.heart(x, y);
            case "disk" -> NonlinearTransormations.disk(x, y);
            case "sinus" -> NonlinearTransormations.sinusoidal(x, y);
            case "spherical" -> NonlinearTransormations.spherical(x, y);
            case "polar" -> NonlinearTransormations.polar(x, y);
            default -> new Point(0, 0);
        };
    }

    default void generateSymmetricPoints(Point curPoint, Pixel curPixel, int symmetryAxes, FractalImage image, Rect rectangle) {
        double theta = 0;
        double XMIN = rectangle.XMIN;
        double XMAX = rectangle.XMAX;
        double YMIN = rectangle.YMIN;
        double YMAX = rectangle.YMAX;
        for (int j = 0; j < symmetryAxes; theta += 2 * Math.PI / symmetryAxes, j++) {

            Point rotPoint = rotate(curPoint, theta);

            // Вычисляем координаты точки, а затем задаем цвет
            int xRot = (int) (image.width - Math.floor(((XMAX - rotPoint.x()) / (XMAX - XMIN)) * image.width));
            int yRot = (int) (image.height - Math.floor(((YMAX - rotPoint.y()) / (YMAX - YMIN)) * image.height));

            // Если точка попала в область изображения
            if (xRot >= 0 && xRot < image.width && yRot >= 0 && yRot < image.height) {
                Pixel rotPixel = image.pixels[yRot][xRot];
                rotPixel.r = curPixel.r;
                rotPixel.g = curPixel.g;
                rotPixel.b = curPixel.b;
                rotPixel.hitCount = curPixel.hitCount;
            }
        }
    }

    default Point rotate(@NotNull Point p, double theta) {
        double xRot = p.x() * Math.cos(theta) - p.y() * Math.sin(theta);
        double yRot = p.x() * Math.sin(theta) + p.y() * Math.cos(theta);
        return new Point(xRot, yRot);
    }
}
