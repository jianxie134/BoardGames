package Models;

// A Tile being made means a Piece is created for it
public class Tile {
    private Piece piece;

    public Tile(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
