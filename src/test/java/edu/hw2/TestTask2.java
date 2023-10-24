package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {
    Rectangle rectangle = new Rectangle(5, 7);
    Square square = new Square(5);

    @Test
    @DisplayName("Площадь")
    void checkArea() {
        assertEquals(25, square.area());
        assertEquals(35, rectangle.area());
    }

    @Test
    @DisplayName("Стороны")
    void checkSides() {
        assertEquals(5, square.width);
        assertEquals(5, square.height);
        assertEquals(5, rectangle.width);
        assertEquals(7, rectangle.height);
    }
}
