package edu.project4;

import java.nio.file.Path;
import static edu.project4.ImageFormat.BMP;
import static edu.project4.ImageFormat.PNG;

public class Main {

    public static void main(String[] args) {

        FractalImage image = FractalImage.create(2000, 1000);
        Renderer renderer = new Renderer();
        LogGammaCorrection logGammaCorrection = new LogGammaCorrection();
        renderer.render(image, 20, 10000, 10000, 2);
        logGammaCorrection.correction(image);
        ImageUtils.save(image, Path.of("C:\\Users\\rusla\\Desktop\\img2.png"), PNG);
    }
}
