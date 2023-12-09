package edu.project4.Model;

public record Point(double x, double y) {

    public boolean isInRect(Rect rect) {
        return (x >= rect.xMin && x <= rect.xMax && y >= rect.yMin && y <= rect.yMax);
    }
}
