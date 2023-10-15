package Game;

import Models.Player;
import Models.SBoard;
import Models.Tile;
import Utilities.Input;
import Utilities.Position;

// // Play Super Tic Tac Toe game here
public class STTT extends TTT{
    private SBoard sBoard; // Super Board

    public STTT(SBoard sBoard) {
        super(sBoard);
        this.sBoard = sBoard;
    }

    @Override
    public void welcome() {
        int bDim = sBoard.getDim();
        int sDim = sBoard.getBoards()[0][0].getDim();
        System.out.printf("Now you have a %dx%d big board each with a %dx%d small board inside which looks like down below.\n", bDim, bDim, sDim, sDim );
        printBig();

        tileOption();
    }

    public void printBig() {
        int dim1 = sBoard.getDim();
        int dim2 = sBoard.getInsideDim();
        int len = ((2*dim2)+1) * dim1 + (dim1-1);
        for (int i = 0; i < len; i++) {
            if (i % (2*dim2+2) == 2*dim2+1) {

            } else if (i % 2 == 0) {
                // ------------  -
                for (int j = 0; j < len; j++) {
                    if (j % (2*dim2+2) == 2*dim2+1) {
                        System.out.print(" ");
                    } else if (j % 2 == 0) {
                        System.out.print("-");
                    } else {
                        System.out.print("---");
                    }
                }
                System.out.println();
            } else {
                // |   |   |   | |
                for (int j = 0; j < len; j++) {
                    int row1 = i / (2*dim2+2);
                    int column1 = j / (2*dim2+2);
                    int row2 = (i % (2*dim2+2)) / 2;
                    int column2 = (j % (2*dim2+2)) / 2;
                    Tile tile;
                    if (j % (2*dim2+2) == 2*dim2+1) {
                        System.out.print("-");
                    } else if (j % 2 == 0) {
                        System.out.print("|");
                    } else if ((tile = sBoard.getBoards()[row1][column1]
                            .getTiles()[row2][column2]) != null) {
                        System.out.print(" " + tile.getPiece().getMark() + " ");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.println();
            }
        }
    }

    @Override
    public Player process(Player[] players) {
        int turn = 0;
        int t = 1;
        Player player;

        int dim = sBoard.getDim();
        int idim = sBoard.getInsideDim();
        int[][] turns = new int[idim][idim];

        while (true) {
            if (t % 2 == 1) {
                player = players[0];
            } else {
                player = players[1];
            }
            System.out.println("Player " + player.getId() +"'s turn");

            // Choose the board
            Position position = selectBoard();
            int row = position.getRow();
            int column = position.getColumn();

            // Show the board and make a move on the board
            board = sBoard.getBoards()[row][column]; // use 'board' to do the small board's jobs
            System.out.println("Make a move on this board:");
            print();
            tile(player); // go-back function? need Override
            turns[row][column]++;

            Player winner = judge(players, turns[row][column]);
            // If a small board has a winner, then
            if (winner != null) { // also judge the small board
                System.out.println("Player " + player.getId() + " has got board " + position.getPosition() + "!");
                // Get the player's Piece for the big tiles
                sBoard.getTiles()[row][column] = new Tile(winner.getPiece());
                turn++; // Time for big board to do turn++

                // And the finalWinner might show up
                board = sBoard; // use 'board' to do the big board's job
                Player finalWinner = judge(players, turn);
                if (finalWinner != null) {
                    return finalWinner;
                }
            }

            printBig();

            // If no winner for now, we have a tie which also might cause a finalWinner because of full turn
            if (turns[row][column] == idim*idim-1) {
                // Set a new Piece for tie-Tile? Maybe no need

                turn++;

                board = sBoard; // use 'board' to do the big board's job
                Player finalWinner = judge(players, turn);
                if (finalWinner != null) {
                    return finalWinner;
                } else if (turn == dim*dim - 1) {
                    return null;
                }
            }

            t++;
        }
    }

    public Position selectBoard() {
        int dim = sBoard.getDim();
        while (true) {
            int pos = Input.wIntRange(1, dim*dim, "board");
            Position position = new Position(pos, dim);

            // Check if the small board game ends
            if (checkBoard(position)) {
                return position;
            } else {
                System.err.println("The board game you chose has ended. Please choose another one.");
            }
        }
    }

    public boolean checkBoard(Position position) {
        return sBoard.getTiles()[position.getRow()][position.getColumn()] == null;
    }

    @Override
    public void tileOption() {
        System.out.println("Players choose the board you wanna tile first, and then make a move");
    }
}
