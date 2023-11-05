package edu.project2;

import org.jetbrains.annotations.NotNull;
import static edu.project2.CellType.WALL;

public final class Renderer {

    private static final String WALL_CELL = "#";

    private static final String PASSAGE_CELL = " ";

    public static Maze maze = null;

    public Renderer(Maze maze) {
        Renderer.maze = maze;
    }

    public @NotNull String render(@NotNull Maze maze) {

        StringBuilder renderedMaze = new StringBuilder();

        for (int y = 0; y < maze.height; y++) {

            for (int x = 0; x < maze.width; x++) {

                Cell cell = maze.getCell(y, x);

                if (cell.type == WALL) {
                    renderedMaze.append(WALL_CELL);
                } else {
                    renderedMaze.append(PASSAGE_CELL);
                }

                if (x == maze.width - 1 && maze.height % 2 == 0) {
                    renderedMaze.append(WALL_CELL);
                }
            }

            renderedMaze.append("\n");

            if (y == maze.width - 1 && maze.height % 2 == 0) {
                renderedMaze.append((WALL_CELL).repeat(Math.max(0, maze.width + 1)));
            }
        }

        return renderedMaze.toString();
    }
}
