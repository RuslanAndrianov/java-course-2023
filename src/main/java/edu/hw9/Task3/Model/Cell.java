package edu.hw9.Task3.Model;

import java.util.Objects;

public class Cell {

    public final int x;

    public final int y;

    public CellType type;

    public Cell parent;

    public Cell(int y, int x, CellType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.parent = null;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        Cell cell = (Cell) o;
        return (x == cell.x) && (y == cell.y) && (type == cell.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x, type);
    }
}
