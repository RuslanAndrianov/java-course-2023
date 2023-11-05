package edu.project2;

import java.util.List;
import java.util.Stack;
import org.jetbrains.annotations.NotNull;
import static edu.project2.CellType.PASSAGE;

@SuppressWarnings("HideUtilityClassConstructor")
public class DFSGenerator {

    public static @NotNull Maze generateMaze(int height, int width) {

        // С самого начала лабиринт заполнен стенами (см. Maze)
        Maze maze = new Maze(height, width);

        // Стек для ячеек
        Stack<Cell> stack = new Stack<>();

        // Начинаем DFS с рандомной ячейки
        Cell startCell = maze.getCell(1, 1);

        // Стартовая ячейка является проходом
        startCell.type = PASSAGE;

        // Добавляем ее в стек
        stack.push(startCell);

        // Пока в стеке есть ячейки
        while (!stack.isEmpty()) {
            Cell currentCell = stack.pop();
            List<Cell> unvisitedNeighbors = maze.getUnvisitedNeighbors(currentCell);

            if (!unvisitedNeighbors.isEmpty()) {
                stack.push(currentCell);

                // Берем рандомного соседа
                Cell randomNeighbor = unvisitedNeighbors.get((int) (Math.random() * unvisitedNeighbors.size()));

                // Удаляем стену, ведущую к соседу
                maze.removeWall(currentCell, randomNeighbor);

                // Делаем соседа проходом
                randomNeighbor.type = PASSAGE;

                // Добавляем соседа в стек
                stack.push(randomNeighbor);
            }
        }

        return maze;
    }
}
