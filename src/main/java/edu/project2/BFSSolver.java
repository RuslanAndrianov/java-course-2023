package edu.project2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static edu.project2.CellType.PASSAGE;
import static edu.project2.CellType.VISITED;

@SuppressWarnings("HideUtilityClassConstructor")
public class BFSSolver {

    public static @Nullable List<Coordinate> solveMaze(@NotNull Maze maze,
        @NotNull Coordinate start, @NotNull Coordinate end) {

        Cell startCell = maze.getCell(start.y(), start.x());
        Cell endCell = maze.getCell(end.y(), end.x());

        Queue<Cell> queue = new LinkedList<>();
        queue.offer(startCell);

        while (!queue.isEmpty()) {
            Cell currentCell = queue.poll();

            if (currentCell.equals(endCell)) {
                return reconstructPath(currentCell);
            }

            currentCell.type = VISITED;

            List<Cell> neighbors = maze.getNeighbors(currentCell.y, currentCell.x, 1);
            for (Cell neighbor : neighbors) {
                if (neighbor.type == PASSAGE) {
                    queue.offer(neighbor);
                    neighbor.parent = currentCell;
                }
            }
        }

        return null;
    }

    private static @NotNull List<Coordinate> reconstructPath(Cell endCell) {
        List<Coordinate> path = new LinkedList<>();
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
