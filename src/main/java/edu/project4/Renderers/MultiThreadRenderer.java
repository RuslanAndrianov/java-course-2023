package edu.project4.Renderers;

import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import edu.project4.Model.Point;
import edu.project4.Model.Rect;
import edu.project4.Transformations.AffineTransformation;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

public class MultiThreadRenderer implements Renderer {

    private static final int NUMBER_OF_THREADS = 10;
    private final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public void executeIterations(
        FractalImage image, @NotNull String transformType, int affCount,
        int iterPerSample, int symmetryAxes, @NotNull Rect rect, AffineTransformation[] affineTransformations
    ) {

        double xMin = rect.xMin;
        double xMax = rect.xMax;
        double yMin = rect.yMin;
        double yMax = rect.yMax;

        // Берем рандомную начальную точку
        double newX = ThreadLocalRandom.current().nextDouble(xMin, xMax);
        double newY = ThreadLocalRandom.current().nextDouble(yMin, yMax);

        // Первые 20 итераций точку не рисуем, т.к. сначала надо найти начальную
        for (int step = START_STEPS; step < iterPerSample; step++) {

            // Выбираем одно из аффинных преобразований
            int i = ThreadLocalRandom.current().nextInt(0, affCount);
            AffineTransformation affTrans = affineTransformations[i];

            // и применяем его
            double x = affTrans.a * newX + affTrans.b * newY + affTrans.c;
            double y = affTrans.d * newX + affTrans.e * newY + affTrans.f;

            // Применяем нелинейное преобразование
            Point point = nonlinearTransform(transformType, x, y);
            newX = point.x();
            newY = point.y();

            Point curPoint = new Point(newX, newY);

            if (step >= 0 && curPoint.isInRect(rect)) {

                // Вычисляем координаты точки, а затем задаем цвет
                int xPixel = (int) (image.width - Math.floor(((xMax - newX) / (xMax - xMin)) * image.width));
                int yPixel = (int) (image.height - Math.floor(((yMax - newY) / (yMax - yMin)) * image.height));

                // Если точка попала в область изображения
                if (xPixel < image.width && yPixel < image.height) {

                    Pixel curPixel = image.pixels[yPixel][xPixel];
                    synchronized (curPixel) {

                        // то проверяем, первый ли раз попали в нее
                        if (curPixel.hitCount == 0) {
                            // Попали в первый раз, берем стартовый цвет у соответствующего аффинного преобразования
                            curPixel.r = affTrans.color.getRed();
                            curPixel.g = affTrans.color.getGreen();
                            curPixel.b = affTrans.color.getBlue();
                        } else {
                            // Попали не в первый раз, считаем так:
                            curPixel.r = (curPixel.r + affTrans.color.getRed()) / 2;
                            curPixel.g = (curPixel.g + affTrans.color.getGreen()) / 2;
                            curPixel.b = (curPixel.b + affTrans.color.getBlue()) / 2;
                        }
                        // Увеличиваем счетчик точки на единицу
                        curPixel.hitCount++;

                        // Учет поворотной симметрии
                        generateSymmetricPoints(curPoint, curPixel, symmetryAxes, image, rect);
                    }
                }
            }
        }
    }

    public void render(
        FractalImage image,
        @NotNull String transformType,
        int affCount,
        int samples,
        int iterPerSample,
        int symmetryAxes
    ) {

        // Прямоугольник для генерации точек
        Rect rect = new Rect(image, transformType);

        // Генерируем affCount аффинных преобразований со стартовыми цветами
        AffineTransformation[] affineTransformations = generateAffineTransformations(affCount);

        for (int num = 0; num < samples; num++) {
            executorService.execute(() -> executeIterations(image, transformType, affCount,
                iterPerSample, symmetryAxes, rect, affineTransformations
            ));
        }
        executorService.shutdown();
    }
}
