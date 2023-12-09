package edu.project4;

import edu.project4.Model.FractalImage;
import edu.project4.Model.ImageUtils;
import edu.project4.Renderers.MultiThreadRenderer;
import edu.project4.Renderers.SingleThreadRenderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.project4.Model.ImageFormat.PNG;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRenderers {

    @Test
    @DisplayName("Тест однопоточного рендера")
    void testSingleThreadRenderer() {
        FractalImage image = FractalImage.create(1920, 1080);
        var logGammaCorrection = new LogGammaCorrection(2.2);
        var singleThreadRenderer = new SingleThreadRenderer();
        singleThreadRenderer.render(image, "polar", 20, 10000, 10000, 2);
        logGammaCorrection.correction(image);
        Path pathToFile = Path.of(System.getProperty("user.dir") + "\\out\\img\\img_single.png");
        ImageUtils.save(image, pathToFile, PNG);
        assertTrue(Files.exists(pathToFile));
    }

    @Test
    @DisplayName("Тест многопоточного рендера")
    void testMultiThreadRenderer() {
        FractalImage image = FractalImage.create(1920, 1080);
        var logGammaCorrection = new LogGammaCorrection(2.2);
        var multiThreadRenderer = new MultiThreadRenderer();
        multiThreadRenderer.render(image, "polar", 20, 10000, 10000, 2);
        logGammaCorrection.correction(image);
        Path pathToFile = Path.of(System.getProperty("user.dir") + "\\out\\img\\img_multi.png");
        ImageUtils.save(image, pathToFile, PNG);
        assertTrue(Files.exists(pathToFile));
    }

    @Test
    @DisplayName("Сравнение производительности")
    void testSingleVSMulti() {
        int NANOSEC_TO_MILLISEC = 1_000_000;
        long startSingle = System.nanoTime();
        testSingleThreadRenderer();
        long endSingle = System.nanoTime();
        double workTimeSingle = (double) ((endSingle - startSingle) / NANOSEC_TO_MILLISEC);
        long startMulti = System.nanoTime();
        testMultiThreadRenderer();
        long endMulti = System.nanoTime();
        double workTimeMulti = (double) ((endMulti - startMulti) / NANOSEC_TO_MILLISEC);
        System.out.println("Время выполнения однопоточной программы: " + workTimeSingle + " мс");
        System.out.println("Время выполнения многопоточной программы: " + workTimeMulti + " мс");
        assertTrue(workTimeSingle > workTimeMulti);
    }
}
