package Models;

// A piece holds a player's mark
public class Piece {
    private String mark;

    public Piece(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public boolean isEq(Piece piece) {
        if (mark.equals(piece.getMark())) {
            return true;
        }
        return false;
    }
}
