package Game;

import Models.Board;
import Models.Player;
import Models.Tile;

// An abstract class which implements Game Interface, used for extension of the board games
public abstract class BoardGame implements Game{
    protected Board board;

    public BoardGame(Board board) {
        this.board = board;
    }

    public void print() {
        int dim = board.getDim();
        Tile[][] tiles = board.getTiles();

        for (int i = 0; i <= dim * 2; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < dim; j++) {
                    System.out.print("-----");
                }
                System.out.println("-");
            } else {
                for (int j = 0; j <= dim*2; j++) {
                    if (j % 2 == 0) {
                        System.out.print("| ");
                    } else {
                        if (tiles[i/2][j/2] == null) {
                            int pos = (i/2)*dim + (j/2)+1;
                            System.out.print(pos + " ");
                            if (Integer.toString(pos).length() == 1) {
                                System.out.print(" ");
                            }
                        } else {
                            System.out.print(tiles[i/2][j/2].getPiece().getMark() + "  ");
                        }
                    }
                }
                System.out.println();
            }
        }
    }

    @Override
    public void welcome() {
        int dim = board.getDim();
        System.out.printf("Now you have a %dx%d board down below.\n", dim, dim);
        print();

        tileOption();
    }

    @Override
    public Player process(Player[] players) {
        int turn = 0;
        Player player;
        int dim = this.board.getDim();

        while (true) {
            if (turn % 2 == 0) {
                player = players[0];
            } else {
                player = players[1];
            }
            System.out.println("Player " + player.getId() +"'s turn");
            tile(player);
            turn++;

            print();

            Player winner = judge(players, turn);
            if (winner != null || turn == dim*dim - 1) {
                return winner;
            }
        }
    }

    public abstract void tile(Player player);

    public abstract void tileOption();
}
