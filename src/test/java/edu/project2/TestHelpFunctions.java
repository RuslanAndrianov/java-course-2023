package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project2.CellType.PASSAGE;
import static edu.project2.CellType.WALL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHelpFunctions {

    @Test
    @DisplayName("Тест метода removeWall")
    void removeWall() {
        Maze maze = new Maze(5, 5);
        Renderer renderer = new Renderer(maze);
        Cell first = maze.getCell(1, 1);
        Cell middle = maze.getCell(1, 2);
        Cell second = maze.getCell(1, 3);

        first.type = PASSAGE;
        middle.type = WALL;
        second.type = PASSAGE;

        System.out.println(renderer.render(maze));
        maze.removeWall(first, second);
        System.out.println(renderer.render(maze));

        assertEquals(PASSAGE, middle.type);
    }
}
