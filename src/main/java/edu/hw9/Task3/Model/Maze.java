package edu.hw9.Task3.Model;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import static edu.hw9.Task3.Model.CellType.PASSAGE;
import static edu.hw9.Task3.Model.CellType.WALL;

public class Maze {

    public int height;

    public int width;

    public Cell[][] grid;

    public Maze(int height, int width) {

        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];

        // С самого начала лабиринт заполнен стенами
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(y, x, WALL);
            }
        }
    }

    public Cell getCell(int y, int x) {

        if (isValidCell(y, x)) {
            return grid[y][x];
        }
        return null;
    }

    public List<Cell> getUnvisitedNeighbors(@NotNull Cell cell) {

        int y = cell.y;
        int x = cell.x;
        List<Cell> neighbors = getNeighbors(y, x, 2);
        List<Cell> unvisitedNeighbors = new ArrayList<>();

        for (Cell neighbor : neighbors) {
            if (neighbor.type == WALL) {
                unvisitedNeighbors.add(neighbor);
            }
        }

        return unvisitedNeighbors;
    }

    public List<Cell> getNeighbors(int y, int x, int dist) {

        List<Cell> neighbors = new ArrayList<>();

        if (isValidCell(y - dist, x)) {
            neighbors.add(grid[y - dist][x]);
        }
        if (isValidCell(y + dist, x)) {
            neighbors.add(grid[y + dist][x]);
        }
        if (isValidCell(y, x - dist)) {
            neighbors.add(grid[y][x - dist]);
        }
        if (isValidCell(y, x + dist)) {
            neighbors.add(grid[y][x + dist]);
        }

        return neighbors;
    }

    private boolean isValidCell(int y, int x) {
        return (y >= 0) && (y < this.height) && (x >= 0) && (x < this.width);
    }

    public void removeWall(@NotNull Cell cell1, @NotNull Cell cell2) {

        int xDiff = cell2.x - cell1.x;
        int yDiff = cell2.y - cell1.y;

        int addX = (xDiff != 0) ? (xDiff / Math.abs(xDiff)) : 0;
        int addY = (yDiff != 0) ? (yDiff / Math.abs(yDiff)) : 0;

        Cell target = new Cell(cell1.y + addY, cell1.x + addX, PASSAGE);
        this.getCell(target.y, target.x).type = PASSAGE;
    }



}
