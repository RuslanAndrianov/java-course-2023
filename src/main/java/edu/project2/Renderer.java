package edu.project2;

import org.jetbrains.annotations.NotNull;

public final class Renderer {

    private static final String WALL_CELL = "#";

    private static final String PASSAGE_CELL = " ";

    private static final String PATH_CELL = "·";

    public static Maze maze = null;

    public Renderer(Maze maze) {
        Renderer.maze = maze;
    }

    public @NotNull String render(@NotNull Maze maze) {

        StringBuilder renderedMaze = new StringBuilder();

        for (int y = 0; y < maze.height; y++) {
            for (int x = 0; x < maze.width; x++) {

                Cell cell = maze.getCell(y, x);

                switch (cell.type) {
                    case PASSAGE, VISITED -> renderedMaze.append(PASSAGE_CELL);
                    case WALL -> renderedMaze.append(WALL_CELL);
                    case PATH -> renderedMaze.append(PATH_CELL);
                }

                // Дорендериваем правую границу в случае четной ширины
                if (x == maze.width - 1 && maze.width % 2 == 0) {
                    renderedMaze.append(WALL_CELL);
                }
            }

            renderedMaze.append("\n");

            // Дорендериваем нижнюю границу в случае четной высоты
            if (y == maze.height - 1 && maze.height % 2 == 0) {
                renderedMaze.append((WALL_CELL).repeat(maze.width));

                // Если и ширина, и высота четные, надо дорендерить нижнюю правую ячейку-стену
                if (maze.width % 2 == 0) {
                    renderedMaze.append(WALL_CELL);

                }

                renderedMaze.append("\n");
            }
        }

        return renderedMaze.toString();
    }
}
