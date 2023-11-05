package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static edu.project2.CellType.PASSAGE;
import static org.assertj.core.api.Assertions.assertThat;

public class TestSolvers {

    Maze maze1 = new Maze(5, 5);
    {
        maze1.getCell(1, 1).type = PASSAGE;
        maze1.getCell(1, 2).type = PASSAGE;
        maze1.getCell(1, 3).type = PASSAGE;
        maze1.getCell(2, 3).type = PASSAGE;
        maze1.getCell(3, 3).type = PASSAGE;
        maze1.getCell(3, 2).type = PASSAGE;
        maze1.getCell(3, 1).type = PASSAGE;
    }

    List<Coordinate> expectedPath1 = new ArrayList<>(List.of(
        new Coordinate(1, 1),
        new Coordinate(1, 2),
        new Coordinate(1, 3),
        new Coordinate(2, 3),
        new Coordinate(3, 3)
    ));

    Coordinate start1 = new Coordinate(1, 1);

    Coordinate end1 = new Coordinate(3, 3);


    @Test
    @DisplayName("Тест DFSSolver")
    public void testDFSSolver() {
        System.out.println(new Renderer(maze1).render(maze1));
        List<Coordinate> actualPath = DFSSolver.solveMaze(maze1, start1, end1);

        assertThat(actualPath).isEqualTo(expectedPath1);
    }

    @Test
    @DisplayName("Тест BFSSolver")
    public void testBFSSolver() {
        System.out.println(new Renderer(maze1).render(maze1));
        List<Coordinate> actualPath = BFSSolver.solveMaze(maze1, start1, end1);

        assertThat(actualPath).isEqualTo(expectedPath1);
    }
}
