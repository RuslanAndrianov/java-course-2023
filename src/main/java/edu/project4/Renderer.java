package edu.project4;

import java.util.concurrent.ThreadLocalRandom;

public class Renderer {

    void render(FractalImage image, int affCount, int samples, int iterPerSample, int symmetry) {

        // heart 2.5
        // disk 0.5
        // sinus 0.5
        // spherical 0.5
        double k = 0.5;

        // Генерируем affCount аффинных преобразований со стартовыми цветами
        AffineTransformation[] affineTransformations = new AffineTransformation[affCount];
        for (int i = 0; i < affCount; i++) {
            affineTransformations[i] = new AffineTransformation();
        }

        // Прямоугольник
        double XMIN, XMAX, YMIN, YMAX;
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

        for (int num = 0; num < samples; num++) {

            // Берем рандомную начальную точку
            double newX = ThreadLocalRandom.current().nextDouble(XMIN, XMAX);
            double newY = ThreadLocalRandom.current().nextDouble(YMIN, YMAX);

            // Первые 20 итераций точку не рисуем, т.к. сначала надо найти начальную
            for (int step = -20; step < iterPerSample; step++) {
                // Выбираем одно из аффинных преобразований
                int i = ThreadLocalRandom.current().nextInt(0, affCount);
                AffineTransformation affTrans = affineTransformations[i];

                // и применяем его
                double x = affTrans.a * newX + affTrans.b * newY + affTrans.c;
                double y = affTrans.d * newX + affTrans.e * newY + affTrans.f;

                // Применяем нелинейное преобразование;
                Point point = NonlinearTransormations.spherical(x, y);
                newX = point.x();
                newY = point.y();

                if (step >= 0 && newX >= XMIN && newX <= XMAX && newY >= YMIN && newY <= YMAX) {

                    // Вычисляем координаты точки, а затем задаем цвет
                    int x1 = (int) (image.width - Math.floor(((XMAX - newX) / (XMAX - XMIN)) * image.width));
                    int y1 = (int) (image.height - Math.floor(((YMAX - newY) / (YMAX - YMIN)) * image.height));

                    // Если точка попала в область изображения
                    if (x1 >= 0 && x1 < image.width && y1 >= 0 && y1 < image.height) {
                        Pixel curPixel = image.pixels[y1][x1];
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

                        double theta = 0;
                        Point curPoint = new Point(newX, newY);
                        for (int j = 0; j < symmetry; theta += 2 * Math.PI / symmetry, j++) {
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
                }
            }
        }
    }

    public Point rotate(Point p, double theta) {
        double xRot = p.x() * Math.cos(theta) - p.y() * Math.sin(theta);
        double yRot = p.x() * Math.sin(theta) + p.y() * Math.cos(theta);
        return new Point(xRot, yRot);
    }
}
