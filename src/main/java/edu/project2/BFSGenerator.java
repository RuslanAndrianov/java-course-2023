package edu.project2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.jetbrains.annotations.NotNull;
import static edu.project2.CellType.PASSAGE;

@SuppressWarnings("HideUtilityClassConstructor")
public class BFSGenerator {

    public static @NotNull Maze generateMaze(int height, int width) {

        // С самого начала лабиринт заполнен стенами (см. Maze)
        Maze maze = new Maze(height, width);

        // Очередь для ячеек
        Queue<Cell> queue = new LinkedList<>();

        // Начинаем BFS с рандомной ячейки (1, 1)
        Cell startCell = maze.getCell(1, 1);

        // Стартовая ячейка является проходом
        startCell.type = PASSAGE;

        // Добавляем ее в очередь
        queue.add(startCell);

        // Пока в очереди есть ячейки
        while (!queue.isEmpty()) {

            // Берем из очереди очередную ячейку
            Cell currentCell = queue.poll();

            // Получаем список ее непроверенных соседей
            List<Cell> unvisitedNeighbors = maze.getUnvisitedNeighbors(currentCell);

            // Для всех непроверенных соседей
            for (int i = 0; i < unvisitedNeighbors.size(); i++) {

                // Берем рандомного непроверенного соседа
                Cell newCell = unvisitedNeighbors.get((int) (Math.random() * unvisitedNeighbors.size()));

                // Удаляем стену, ведущую к нему
                maze.removeWall(currentCell, newCell);

                // Делаем его проходом
                newCell.type = PASSAGE;

                // Добавляем его в очередь
                queue.add(newCell);
            }
        }

        return maze;
    }
}
