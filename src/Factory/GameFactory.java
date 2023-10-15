package Factory;

import Game.*;
import Models.Board;
import Models.SBoard;
import Utilities.Input;

// Build a board game basics
public class GameFactory {
    public static BoardGame buildGame() {
        System.out.println("Welcome to the Games!");

        // let users select the game
        GameType gameType = selectGame();

        System.out.println("Have fun in " + gameType.getName() + " game!");
        switch (gameType) {
            case TTT:
                return new TTT(buildBoard(3, 5));
            case OC:
                return new OC(buildBoard(6, 6));
            case STTT:
                return new STTT(buildSBoard(3, 5));
            default:
                throw new RuntimeException();
        }
    }

    public static SBoard buildSBoard(int min, int max) {
        if (min < max) {
            System.out.printf("Now you can custom your big board and all the small boards (with one same size).\n" +
                    "Enter a size of the big board first and then the small ones: " +
                    "(all limited %d-%d, %d for %dx%d)\n", min, max, min, min, min);
            int dim1 = Input.wIntRange(min, max, "size");
            int dim2 = Input.wIntRange(min, max, "size");
            return new SBoard(dim1, dim2);
        } else {
            throw new RuntimeException();
        }
    }

    public static Board buildBoard(int min, int max) {
        if (min < max) {
            System.out.printf("Now you can customize your board. Please choose a size of the board: (Limited %d-%d, %d for %dx%d)\n", min, max, min, min, min);
            int dim = Input.wIntRange(min, max, "size");
            return new Board(dim);
        } else if (min == max) {
            return new Board(min);
        } else {
            throw new RuntimeException("Error. Lower limit of the board size is bigger than upper limit.");
        }
    }

    // users select one game to play
    public static GameType selectGame() {
        // print all the games
        GameType.gameChoices();

        GameType game = null;
        while (game == null) {
            game = GameType.searchGame(Input.wInt());
        }
        return game;
    }
}
