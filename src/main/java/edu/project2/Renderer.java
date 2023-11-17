package edu.project2;

import org.jetbrains.annotations.NotNull;

public final class Renderer {

    private static final int TEN = 10;

    private static final String WALL_CELL = "#";

    private static final String PASSAGE_CELL = " ";

    private static final String PATH_CELL = "·";

    public static Maze maze = null;

    public Renderer(Maze maze) {
        Renderer.maze = maze;
    }

    public @NotNull String render(@NotNull Maze maze) throws IllegalStateException {

        StringBuilder renderedMaze = new StringBuilder();

        for (int y = 0; y < maze.height; y++) {

            // Рендер верхних координат
            if (y == 0) {
                int mazeWidth = maze.width;
                int coordHeight = 0;

                while (mazeWidth >= 1) {
                    mazeWidth /= TEN;
                    coordHeight++;
                }

                for (int i = 1; i <= coordHeight; i++) {
                    StringBuilder str = new StringBuilder();
                    for (int x = 0; x <= maze.width; x++) {
                        str.append((x % TEN + "").repeat((int) Math.pow(TEN, coordHeight - i)));
                    }
                    if (maze.width % 2 == 1) {
                        renderedMaze.append(str, 0, maze.width);
                    } else {
                        renderedMaze.append(str, 0, maze.width + 1);
                    }
                    renderedMaze.append("\n");
                }
                renderedMaze.append("\n");
            }

            for (int x = 0; x < maze.width; x++) {

                Cell cell = maze.getCell(y, x);

                switch (cell.type) {
                    case PASSAGE, VISITED -> renderedMaze.append(PASSAGE_CELL);
                    case WALL -> renderedMaze.append(WALL_CELL);
                    case PATH -> renderedMaze.append(PATH_CELL);
                    default -> throw new IllegalStateException("Incorrect cell type");
                }

                // Дорендериваем правую границу в случае четной ширины
                if (x == maze.width - 1 && maze.width % 2 == 0) {
                    renderedMaze.append(WALL_CELL);
                }
            }

            // Рендер правых координат и переход на следующую строку
            renderedMaze.append(" ").append(y).append("\n");

            // Дорендериваем нижнюю границу в случае четной высоты
            if (y == maze.height - 1 && maze.height % 2 == 0) {
                renderedMaze.append((WALL_CELL).repeat(maze.width));

                // Если и ширина, и высота четные, надо дорендерить нижнюю правую ячейку-стену
                if (maze.width % 2 == 0) {
                    renderedMaze.append(WALL_CELL);
                }

                // Рендер правых координат и переход на следующую строку
                renderedMaze.append(" ").append(y + 1).append("\n");
            }
        }

        return renderedMaze.toString();
    }
}
