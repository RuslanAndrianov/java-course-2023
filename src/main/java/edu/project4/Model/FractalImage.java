package edu.project4.Model;

public class FractalImage {

    public Pixel[][] pixels;

    public int width;

    public int height;

    public FractalImage(Pixel[][] pixels, int width, int height) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
    }

    public static FractalImage create(int width, int height) {

        Pixel[][] pixels = new Pixel[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[j][i] = new Pixel(0, 0, 0, 0);
            }
        }
        return new FractalImage(pixels, width, height);
    }

    boolean containsPixel(int x, int y) {
        return (x >= 0) && (x < width) && (y >= 0) && (y < height);
    }

    Pixel getPixel(int x, int y) {
        if (containsPixel(x, y)) {
            return pixels[y][x];
        } else {
            return null;
        }
    }
}
