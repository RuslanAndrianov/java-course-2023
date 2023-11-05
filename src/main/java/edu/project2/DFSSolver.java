package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static edu.project2.CellType.PASSAGE;
import static edu.project2.CellType.VISITED;

@SuppressWarnings("HideUtilityClassConstructor")
public class DFSSolver {

    public static @Nullable List<Coordinate> solveMaze(@NotNull Maze maze,
        @NotNull Coordinate start, @NotNull Coordinate end) {

        Cell startCell = maze.getCell(start.y(), start.x());
        Cell endCell = maze.getCell(end.y(), end.x());

        Stack<Cell> stack = new Stack<>();
        stack.push(startCell);

        while (!stack.isEmpty()) {
            Cell currentCell = stack.pop();

            if (currentCell.equals(endCell)) {
                return reconstructPath(currentCell);
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

        return null;
    }

    private static @NotNull List<Coordinate> reconstructPath(Cell endCell) {
        List<Coordinate> path = new ArrayList<>();
        Cell curCell = endCell;

        while (curCell != null) {
            path.add(new Coordinate(curCell.y, curCell.x));
            curCell = curCell.parent;
        }

        // Обращаем путь от конца до начала
        Collections.reverse(path);
        return path;
    }
}
