package edu.project4;

import edu.project4.Model.FractalImage;
import edu.project4.Model.ImageUtils;
import edu.project4.Renderers.MultiThreadRenderer;
import edu.project4.Renderers.SingleThreadRenderer;
import java.nio.file.Path;
import static edu.project4.Model.ImageFormat.PNG;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber", "MultipleStringLiterals"})
public class Main {

    public static void main(String[] args) {

        var logGammaCorrection = new LogGammaCorrection(2.2);

        var singleThreadRenderer = new SingleThreadRenderer();
        FractalImage image1 = FractalImage.create(1920, 1080);
        Path pathToFile1 = Path.of(System.getProperty("user.dir") + "\\out\\img\\img1.png");
        singleThreadRenderer.render(image1, "polar", 20, 10000, 10000, 2);
        logGammaCorrection.correction(image1);
        ImageUtils.save(image1, pathToFile1, PNG);

        var multiThreadRenderer = new MultiThreadRenderer();
        FractalImage image2 = FractalImage.create(1920, 1080);
        Path pathToFile2 = Path.of(System.getProperty("user.dir") + "\\out\\img\\img2.png");
        multiThreadRenderer.render(image2, "polar", 20, 10000, 10000, 2);
        logGammaCorrection.correction(image2);
        ImageUtils.save(image2, pathToFile2, PNG);
    }
}
