package edu.hw9.Task3.Solvers;

import edu.hw9.Task3.Model.Cell;
import edu.hw9.Task3.Model.Coordinate;
import edu.hw9.Task3.Model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import static edu.hw9.Task3.Model.CellType.PASSAGE;
import static edu.hw9.Task3.Model.CellType.PATH;
import static edu.hw9.Task3.Model.CellType.VISITED;

@SuppressWarnings({"MagicNumber", "HideUtilityClassConstructor", "RegexpSinglelineJava"})
public class DFSSolverMultiThread {

    private static final int NUMBER_OF_THREADS = 10;

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static void executeFunc(Maze maze, @NotNull Stack<Cell> stack, Cell endCell) {
        synchronized (stack) {
            while (!stack.isEmpty()) {
                Cell currentCell = stack.pop();

                if (currentCell.equals(endCell)) {
                    return;
                }

                currentCell.type = VISITED;

                List<Cell> neighbors = maze.getNeighbors(currentCell.y, currentCell.x, 1);
                for (Cell neighbor : neighbors) {
                    if (neighbor.type == PASSAGE) {
                        stack.push(neighbor);
                        neighbor.parent = currentCell;
                    }
                }
            }
        }
    }

    public static @NotNull List<Coordinate> solveMaze(@NotNull Maze maze,
        @NotNull Coordinate start, @NotNull Coordinate end) {

        Cell startCell = maze.getCell(start.y(), start.x());
        Cell endCell = maze.getCell(end.y(), end.x());

        Stack<Cell> stack = new Stack<>();
        stack.push(startCell);

        EXECUTOR_SERVICE.execute(() -> executeFunc(maze, stack, endCell));
        try {
            EXECUTOR_SERVICE.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception!");
        }
        return reconstructPath(endCell);
    }

    private static @NotNull List<Coordinate> reconstructPath(Cell endCell) {
        List<Coordinate> path = new ArrayList<>();
        Cell curCell = endCell;

        while (curCell != null) {
            curCell.type = PATH;
            path.add(new Coordinate(curCell.y, curCell.x));
            curCell = curCell.parent;
        }

        // Обращаем путь от конца до начала
        Collections.reverse(path);
        return path;
    }
}
