package edu.project2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static edu.project2.CellType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestGenerators {

    @Test
    @DisplayName("Тест BFSGenerator")
    void generateMazeBFS() {
        Maze maze = BFSGenerator.generateMaze(5, 5);
        System.out.println(new Renderer(maze).render(maze));
        assertNotNull(maze);
        assertEquals(5, maze.height);
        assertEquals(5, maze.width);

        int passageCount = 0;
        for (int row = 0; row < maze.height; row++) {
            for (int col = 0; col < maze.width; col++) {
                Cell cell = maze.getCell(row, col);
                assertNotNull(cell);
                assertTrue(cell.type == PASSAGE || cell.type == WALL);

                if (cell.type == PASSAGE) {
                    passageCount++;
                }
            }
        }
        assertTrue(passageCount >= 2);
    }

    @Test
    @DisplayName("Тест DFSGenerator")
    void generateMazeDFS() {
        Maze maze = DFSGenerator.generateMaze(5, 5);
        System.out.println(new Renderer(maze).render(maze));
        assertNotNull(maze);
        assertEquals(5, maze.height);
        assertEquals(5, maze.width);

        int passageCount = 0;
        for (int row = 0; row < maze.height; row++) {
            for (int col = 0; col < maze.width; col++) {
                Cell cell = maze.getCell(row, col);
                assertNotNull(cell);
                assertTrue(cell.type == PASSAGE || cell.type == WALL);

                if (cell.type == PASSAGE) {
                    passageCount++;
                }
            }
        }
        assertTrue(passageCount >= 2);
    }

    @Test
    @DisplayName("Тест метода removeWall")
    void removeWall() {
        Maze maze = new Maze(3, 3);
        Cell first = maze.getCell(0, 0);
        Cell second = maze.getCell(0, 2);
        Cell middle = maze.getCell(0, 1);

        maze.removeWall(first, second);
        assertEquals(PASSAGE, middle.type);
    }
}
