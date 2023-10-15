package Utilities;

// Utilities for 1d-2d change on board
public class Position {
    private int position;
    private int row, column;

    public Position(int position, int dim) {
        this.position = position;
        this.row = (this.position-1) / dim;
        this.column = (this.position-1) % dim;
    }

    public int getPosition() {
        return position;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
