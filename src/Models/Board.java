package Models;

import Utilities.Position;

// Board basic stuff which can be tiled
public class Board {
    protected int dim;
    protected Tile[][] tiles;

    public Board(int dim) {
        this.dim = dim;
        tiles = new Tile[dim][dim];
    }

    public int getDim() {
        return dim;
    }

    public boolean makeTile(Position position, Piece piece) {
        int row = position.getRow();
        int column = position.getColumn();
        if (tiles[row][column] == null) {
            tiles[row][column] = new Tile(piece);
//            System.out.printf("You made a tile at [%d][%d], position[%d]\n", row, column, position.getPosition());
            return true;
        }
        return false;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
