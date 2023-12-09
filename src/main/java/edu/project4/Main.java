package edu.project4;

import edu.project4.Model.FractalImage;
import edu.project4.Model.ImageUtils;
import edu.project4.Renderers.SingleThreadRenderer;
import java.nio.file.Path;
import static edu.project4.Model.ImageFormat.PNG;

public class Main {

    public static void main(String[] args) {

        FractalImage image = FractalImage.create(1920, 1080);
        var logGammaCorrection = new LogGammaCorrection(2.2);
        var singleThreadRenderer = new SingleThreadRenderer();
        singleThreadRenderer.render(image, "polar", 20, 10000, 10000, 2);
        logGammaCorrection.correction(image);
        ImageUtils.save(image, Path.of("C:\\Users\\rusla\\Desktop\\img2.png"), PNG);
    }
}
