package Game;

import Models.*;
import Utilities.Input;
import Utilities.Position;

// Play Tic Tac Toe game here
public class TTT extends BoardGame{
    public TTT(Board board) {
        super(board);
    }

    @Override
    public void tile(Player player) {
        int dim = board.getDim();

        while (true) {
            int position = Input.wIntRange(1, dim*dim, "position");
            if (board.makeTile(new Position(position, dim), player.getPiece())) {
                break;
            }
            System.err.println("The position is already with a tile. Choose a position again.");
        }
    }

    @Override
    public void tileOption() {
        System.out.println("Players can put your chosen mark at an empty space.");
    }

    @Override
    public Player judge(Player[] players, int turn) {

        int winPoint = board.getDim();
        // Not enough turns to have a winner
        if (turn < winPoint) {
            return null;
        }

//        System.out.println("judge begin");
        int dim = board.getDim();
        int winCount = dim;
        Tile[][] tiles= board.getTiles();

        // row
        for (int i = 0; i < dim; i++) {
            Tile tempTile = tiles[i][0];

            if (tempTile == null) {
                continue;
            }
            Piece tempPiece = tempTile.getPiece();

            for (int j = 1; j < winCount; j++) {
                if (tiles[i][j] == null) {
                    break;
                }
                if (!tiles[i][j].getPiece().isEq(tempPiece)) {
                    break;
                }
                // Till the last one of this row, we got the same Tile (not null) every time, which means we have a winner
                if (j == winCount-1) {
                    return checkPlayer(players, tempPiece);
                }
            }
        }

        // column
        for (int j = 0; j < dim; j++) {
            Tile tempTile = tiles[0][j];

            if (tempTile == null) {
                continue;
            }

            Piece tempPiece = tempTile.getPiece();

            for (int i = 1; i < winCount; i++) {
                if (tiles[i][j] == null) {
                    break;
                }
                if (!tiles[i][j].getPiece().isEq(tempPiece)) {
                    break;
                }
                // Till the last one of this row, we got the same Tile (not null) every time, which means we have a winner
                if (i == winCount-1) {
                    return checkPlayer(players, tempPiece);
                }
            }
        }

        // cross
        for (int i = 1; i < winCount; i++) {
            Tile tempTile = tiles[0][0];
//            System.out.println(tempTile);
            if (tempTile == null) {
                break;
            }

            Piece tempPiece = tempTile.getPiece();
            int j = i;
            if (tiles[i][j] == null || !tiles[i][j].getPiece().isEq(tempPiece)) {
                break;
            }

            if (i == winCount-1) {
                return checkPlayer(players, tempPiece);
            }
        }

        for (int i = 0; i < winCount; i++) {
            Tile tempTile = tiles[0][dim-1];
            if (tempTile == null) {
                break;
            }

            Piece tempPiece = tempTile.getPiece();
            int j = dim - i - 1;
            if (tiles[i][j] == null || !tiles[i][j].getPiece().isEq(tempPiece)) {
                break;
            }

            if (i == winCount-1) {
                return checkPlayer(players, tempPiece);
            }
        }

        return null;
    }

    public static Player checkPlayer(Player[] players, Piece piece) {
        if (piece.getMark().equals(players[0].getMark())) {
            return players[0];
        }
        return players[1];
    }
}
