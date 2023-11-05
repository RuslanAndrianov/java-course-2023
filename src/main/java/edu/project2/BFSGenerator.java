package edu.project2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import org.jetbrains.annotations.NotNull;
import static edu.project2.CellType.PASSAGE;

@SuppressWarnings("HideUtilityClassConstructor")
public class BFSGenerator {

    public static @NotNull Maze generateMaze(int height, int width) {

        // С самого начала лабиринт заполнен стенами (см. Maze)
        Maze maze = new Maze(height, width);

        // Очередь для ячеек
        Queue<Cell> queue = new LinkedList<>();

        // Начинаем BFS с рандомной ячейки
        int startX = 1;
        int startY = 1;
        Cell startCell = maze.getCell(startY, startX);

        // Стартовая ячейка является проходом
        startCell.type = PASSAGE;

        // Добавляем ее в очередь
        queue.add(startCell);

        // Пока в очереди есть ячейки
        while (!queue.isEmpty()) {
            Cell currentCell = queue.poll();
            List<Cell> unvisitedNeighbors = maze.getUnvisitedNeighbors(currentCell);

            // Рандомно перемешиваем соседей
            Collections.shuffle(unvisitedNeighbors, new Random());

            for (Cell unvisitedNeighbor : unvisitedNeighbors) {
                int newX = unvisitedNeighbor.x;
                int newY = unvisitedNeighbor.y;

                if (newX == 0
                    || newX == maze.width - 1
                    || newY == 0
                    || newY == maze.height - 1) {
                    continue;
                }

                // Удаляем стену, ведущую к соседу
                maze.removeWall(currentCell, unvisitedNeighbor);

                // Делаем соседа проходом
                unvisitedNeighbor.type = PASSAGE;

                // Добавляем соседей в очередь
                queue.add(maze.getCell(newY, newX));
            }
        }

        return maze;
    }
}
