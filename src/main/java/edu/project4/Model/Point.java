package edu.project4.Model;

public record Point(double x, double y) {

    public boolean isInRect(Rect rect) {
        return (x >= rect.XMIN && x <= rect.XMAX && y >= rect.YMIN && y <= rect.YMAX);
    }
}
