package edu.project4;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class ImageUtils {

    static void save(FractalImage image, Path fileName, ImageFormat format) {

        BufferedImage bufferedImage =
            new BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < image.width; x++) {
            for (int y = 0; y < image.height; y++) {
                Pixel pixel = image.getPixel(x, y);
                Color color = new Color(pixel.r, pixel.g, pixel.b);
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }
        try {
            ImageIO.write(bufferedImage, format.name(), fileName.toFile());
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл!");
        }
    }
}
