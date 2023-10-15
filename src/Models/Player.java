package Models;

public class Player {
    private Piece piece;
    private int id;
    private int winTime = 0;

    public Player(int id) {
        this.id = id;
    }

    public Player(Piece piece, int id) {
        this.piece = piece;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getMark() {
        return piece.getMark();
    }

    public int getWinTime() {
        return winTime;
    }

    public void win() {
        this.winTime += 1;
    }
}
