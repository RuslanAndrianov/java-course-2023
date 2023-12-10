package edu.hw9.TestTask3;

import edu.hw9.Task3.Generators.BFSGenerator;
import edu.hw9.Task3.Solvers.BFSSolver;
import edu.hw9.Task3.Model.Coordinate;
import edu.hw9.Task3.Generators.DFSGenerator;
import edu.hw9.Task3.Solvers.DFSSolver;
import edu.hw9.Task3.Solvers.DFSSolverMultiThread;
import edu.hw9.Task3.Model.Maze;
import edu.hw9.Task3.Renderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static edu.hw9.Task3.Model.CellType.PASSAGE;
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

    Renderer render1 = new Renderer(maze1);

    @Test
    @DisplayName("Тест DFSSolver")
    public void testDFSSolver() {
        System.out.println("\n\nТест DFSSolver\n\n");
        System.out.println(render1.render(maze1));
        List<Coordinate> actualPath = DFSSolver.solveMaze(maze1, start1, end1);
        System.out.println(render1.render(maze1));

        assertThat(actualPath).isEqualTo(expectedPath1);
    }

    @Test
    @DisplayName("Тест BFSSolver")
    public void testBFSSolver() {
        System.out.println("\n\nТест BFSSolver\n\n");
        System.out.println(render1.render(maze1));
        List<Coordinate> actualPath = BFSSolver.solveMaze(maze1, start1, end1);
        System.out.println(render1.render(maze1));

        assertThat(actualPath).isEqualTo(expectedPath1);
    }

    @Test
    @DisplayName("Тест DFSSolverMultiThread")
    public void testDFSSolverMultiThread() {
        System.out.println("\n\nТест DFSSolverMultiThread\n\n");
        System.out.println(render1.render(maze1));
        List<Coordinate> actualPath = DFSSolverMultiThread.solveMaze(maze1, start1, end1);
        System.out.println(render1.render(maze1));

        assertThat(actualPath).isEqualTo(expectedPath1);
    }

    void BFS(int y, int x) {
        System.out.println("x = " + x + ", y = " + y + "\n");
        Maze maze = BFSGenerator.generateMaze(y, x);
        Coordinate start = new Coordinate(1, 1);

        int endX = 0, endY = 0;
        for (int i = 0; i < maze.width * maze.height; i++) {
            endX = (int) (Math.random() * maze.width);
            endY = (int) (Math.random() * maze.height);
            if (maze.grid[endY][endX].type == PASSAGE) {
                break;
            }
        }
        Coordinate end = new Coordinate(endY, endX);

        BFSSolver.solveMaze(maze, start, end);
        System.out.println(new Renderer(maze).render(maze));
    }

    void DFS(int y, int x) {
        System.out.println("x = " + x + ", y = " + y + "\n");
        Maze maze = DFSGenerator.generateMaze(y, x);
        Coordinate start = new Coordinate(1, 1);

        int endX = 0, endY = 0;
        for (int i = 0; i < maze.width * maze.height; i++) {
            endX = (int) (Math.random() * maze.width);
            endY = (int) (Math.random() * maze.height);
            if (maze.grid[endY][endX].type == PASSAGE) {
                break;
            }
        }
        Coordinate end = new Coordinate(endY, endX);

        DFSSolver.solveMaze(maze, start, end);
        System.out.println(new Renderer(maze).render(maze));
    }

    void DFSMultiThread(int y, int x) {
        System.out.println("x = " + x + ", y = " + y + "\n");
        Maze maze = DFSGenerator.generateMaze(y, x);
        Coordinate start = new Coordinate(1, 1);

        int endX = 0, endY = 0;
        for (int i = 0; i < maze.width * maze.height; i++) {
            endX = (int) (Math.random() * maze.width);
            endY = (int) (Math.random() * maze.height);
            if (maze.grid[endY][endX].type == PASSAGE) {
                break;
            }
        }
        Coordinate end = new Coordinate(endY, endX);

        DFSSolverMultiThread.solveMaze(maze, start, end);
        System.out.println(new Renderer(maze).render(maze));
    }

    @Test
    @DisplayName("Просто красивые выводы с решением")
    void somePrints() {
        System.out.println("\n\nBFS\n\n");
        BFS(11,6);
        BFS(6,11);
        BFS(11,11);
        BFS(6,6);
        BFS(11,7);
        BFS(7,11);
        BFS(6,10);
        BFS(10,6);
        BFS(50,50);
        System.out.println("\n\nDFS\n\n");
        DFS(11,6);
        DFS(6,11);
        DFS(11,11);
        DFS(6,6);
        DFS(11,7);
        DFS(7,11);
        DFS(6,10);
        DFS(10,6);
        DFS(50,50);
        System.out.println("\n\nDFSMultiThread\n\n");
        DFSMultiThread(11,6);
        DFSMultiThread(6,11);
        DFSMultiThread(11,11);
        DFSMultiThread(6,6);
        DFSMultiThread(11,7);
        DFSMultiThread(7,11);
        DFSMultiThread(6,10);
        DFSMultiThread(10,6);
        DFSMultiThread(50,50);
    }
}
