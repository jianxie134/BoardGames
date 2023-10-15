package Game;

import Models.Board;
import Models.Piece;
import Models.Player;
import Models.Tile;
import Utilities.Input;
import Utilities.Position;

// Play Order and Chaos game here
public class OC extends BoardGame {
    public OC(Board board) {
        super(board);
    }

    @Override
    public void tile(Player player) {
        int dim = board.getDim();
        while (true) {
            int pos = Input.wIntRange(1, dim*dim, "position");
            Position position = new Position(pos, dim);
            if (board.getTiles()[position.getRow()][position.getColumn()] == null) {
                int mark = Input.wInt();
                if (mark == 1) {
                    board.makeTile(position, new Piece("X"));
                    break;
                } else if (mark == 2) {
                    board.makeTile(position, new Piece("O"));
                    break;
                } else {
                    System.err.println("Invalid mark. Please choose again.");
                }
            } else {
                System.err.println("The position is already with a tile. Choose a position again.");
            }
        }
    }

    @Override
    public void tileOption() {
        System.out.println("Players can put your 'X' or 'O' at an empty space");
        System.out.println("Enter your position first, and then enter 1 for 'X' and 2 for 'O'");
    }

    @Override
    public Player judge(Player[] players, int turn) {

        int winPoint = 5;
        if (turn < winPoint) {
            return null;
        }

        System.out.println("judge begin");
        int winCount = 5;
        int tempWinCount = winCount;
        int dim = board.getDim();
        Tile[][] tiles= board.getTiles();

        // row
        for (int i = 0; i < dim; i++) {

            Tile tempTile = tiles[i][0];
            Tile tempTile1 = tiles[i][1];

            if (tempTile1 == null) {
                continue;
            }

            if (tempTile == null || !tempTile.getPiece().isEq(tempTile1.getPiece())) {
                winCount += 1;
                tempTile = tempTile1;
            }
            Piece tempPiece = tempTile.getPiece();

            for (int j = 2; j < winCount; j++) {
                if (tiles[i][j] == null || !tempPiece.isEq(tiles[i][j].getPiece())) {
                    break;
                }
                if (j == winCount - 1) {
                    return players[0];
                }
            }
        }
        winCount = tempWinCount;

        // column
        for (int j = 0; j < dim; j++) {

            Tile tempTile = tiles[0][j];
            Tile tempTile1 = tiles[1][j];

            if (tempTile1 == null) {
                continue;
            }

            if (tempTile == null || !tempTile.getPiece().isEq(tempTile1.getPiece())) {
                winCount += 1;
                tempTile = tempTile1;
            }
            Piece tempPiece = tempTile.getPiece();

            for (int i = 2; i < winCount; i++) {
                if (tiles[i][j] == null || !tempPiece.isEq(tiles[i][j].getPiece())) {
                    break;
                }
                if (i == winCount - 1) {
                    return players[0];
                }
            }
        }
        winCount = tempWinCount;


//        // cross
        Tile tempTile = tiles[0][0];
        Tile tempTile1 = tiles[1][1];
        if (tempTile1 != null) {
            if (tempTile == null || !tempTile1.getPiece().isEq(tempTile1.getPiece())) {
                winCount += 1;
                tempTile = tempTile1;
            }
            Piece tempPiece = tempTile.getPiece();

            for (int i = 2; i < winCount; i++) {
                int j = i;
                if (tiles[i][j] == null || !tempPiece.isEq(tiles[i][j].getPiece())) {
                    break;
                }
                if (i == winCount - 1) {
                    return players[0];
                }
            }
        }
        winCount = tempWinCount;

        tempTile = tiles[0][dim-1];
        tempTile1 = tiles[1][dim-2];
        if (tempTile1 != null) {
            if (tempTile == null || !tempTile1.getPiece().isEq(tempTile1.getPiece())) {
                winCount += 1;
                tempTile = tempTile1;
            }
            Piece tempPiece = tempTile.getPiece();

            for (int i = 2; i < winCount; i++) {
                int j = winCount-1-i;
                if (tiles[i][j] == null || !tempPiece.isEq(tiles[i][j].getPiece())) {
                    break;
                }
                if (i == winCount - 1) {
                    return players[0];
                }
            }
        }
        winCount = tempWinCount;

        tempTile = tiles[0][1];
        if (tempTile != null) {
            Piece tempPiece = tempTile.getPiece();
            for (int i = 1; i < winCount; i++) {
                int j = i+1;
                if (tiles[i][j] == null || !tempPiece.isEq(tiles[i][j].getPiece())) {
                    break;
                }
                if (i == winCount - 1) {
                    return players[0];
                }
            }
        }

        tempTile = tiles[1][0];
        if (tempTile != null) {
            Piece tempPiece = tempTile.getPiece();
            for (int j = 1; j < winCount; j++) {
                int i = j+1;
                if (tiles[i][j] == null || !tempPiece.isEq(tiles[i][j].getPiece())) {
                    break;
                }
                if (j == winCount - 1) {
                    return players[0];
                }
            }
        }

        tempTile = tiles[0][dim-2];
        if (tempTile != null) {
            Piece tempPiece = tempTile.getPiece();
            for (int i = 1; i < winCount; i++) {
                int j = dim-2-i;
                if (tiles[i][j] == null || !tempPiece.isEq(tiles[i][j].getPiece())) {
                    break;
                }
                if (i == winCount - 1) {
                    return players[0];
                }
            }
        }

        tempTile = tiles[dim-2][0];
        if (tempTile != null) {
            Piece tempPiece = tempTile.getPiece();
            for (int j = 1; j < winCount; j++) {
                int i = dim-2-j;
                if (tiles[i][j] == null || !tempPiece.isEq(tiles[i][j].getPiece())) {
                    break;
                }
                if (i == winCount - 1) {
                    return players[0];
                }
            }
        }

        return null;
    }
}
