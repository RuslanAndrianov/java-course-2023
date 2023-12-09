package edu.project4;

public class LogGammaCorrection {

    void correction(FractalImage image)
    {
        double max = 0.0;
        double gamma = 2.7;
        Pixel[][] pixels = image.pixels;
        for (int row = 0; row < image.height; row++)
            for (int col = 0; col < image.width; col++)
                if (pixels[row][col].hitCount != 0)
                {
                    pixels[row][col].normal = Math.log10(pixels[row][col].hitCount);
                    if (pixels[row][col].normal > max) {
                        max = pixels[row][col].normal;
                    }
                }
        for (int row = 0; row < image.height; row++)
            for (int col = 0; col < image.width; col++)
            {
                pixels[row][col].normal /= max;
                pixels[row][col].r = (int) (pixels[row][col].r * Math.pow(pixels[row][col].normal,(1.0 / gamma)));
                pixels[row][col].g = (int) (pixels[row][col].g * Math.pow(pixels[row][col].normal,(1.0 / gamma)));
                pixels[row][col].b = (int) (pixels[row][col].b * Math.pow(pixels[row][col].normal,(1.0 / gamma)));
            }
    }
}
