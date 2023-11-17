package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project2.CellType.PASSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRender {

    @Test
    @DisplayName("Тест рендера 1")
    void testRenderer1() {
        Maze maze1 = new Maze(3, 3);
        {
            maze1.getCell(1, 1).type = PASSAGE;
        }

        String printMaze1 =
                """
                012

                ### 0
                # # 1
                ### 2
                """;

        Renderer renderer = new Renderer(maze1);
        assertEquals(renderer.render(maze1), printMaze1);
    }

    @Test
    @DisplayName("Тест рендера 2")
    void testRenderer2() {
        Maze maze1 = new Maze(5, 6);
        {
            maze1.getCell(1, 1).type = PASSAGE;
            maze1.getCell(2, 1).type = PASSAGE;
            maze1.getCell(3, 1).type = PASSAGE;
            maze1.getCell(3, 2).type = PASSAGE;
            maze1.getCell(3, 3).type = PASSAGE;
            maze1.getCell(2, 3).type = PASSAGE;
            maze1.getCell(1, 3).type = PASSAGE;
            maze1.getCell(3, 4).type = PASSAGE;
            maze1.getCell(3, 5).type = PASSAGE;
            maze1.getCell(2, 5).type = PASSAGE;
            maze1.getCell(1, 5).type = PASSAGE;
        }

        String printMaze1 =
            """
            0123456

            ####### 0
            # # # # 1
            # # # # 2
            #     # 3
            ####### 4
            """;

        Renderer renderer = new Renderer(maze1);
        assertEquals(renderer.render(maze1), printMaze1);
    }

    @Test
    @DisplayName("Тест рендера 3")
    void testRenderer3() {
        Maze maze1 = new Maze(6, 6);
        {
            maze1.getCell(1, 1).type = PASSAGE;
            maze1.getCell(1, 2).type = PASSAGE;
            maze1.getCell(1, 3).type = PASSAGE;
            maze1.getCell(1, 4).type = PASSAGE;
            maze1.getCell(1, 5).type = PASSAGE;
            maze1.getCell(2, 5).type = PASSAGE;
            maze1.getCell(3, 5).type = PASSAGE;
            maze1.getCell(4, 5).type = PASSAGE;
            maze1.getCell(5, 5).type = PASSAGE;
            maze1.getCell(5, 4).type = PASSAGE;
            maze1.getCell(5, 3).type = PASSAGE;
            maze1.getCell(5, 2).type = PASSAGE;
            maze1.getCell(5, 1).type = PASSAGE;
            maze1.getCell(4, 1).type = PASSAGE;
            maze1.getCell(3, 1).type = PASSAGE;
            maze1.getCell(3, 2).type = PASSAGE;
            maze1.getCell(3, 3).type = PASSAGE;
        }

        String printMaze1 =
            """
            0123456

            ####### 0
            #     # 1
            ##### # 2
            #   # # 3
            # ### # 4
            #     # 5
            ####### 6
            """;

        Renderer renderer = new Renderer(maze1);
        assertEquals(renderer.render(maze1), printMaze1);
    }
}
