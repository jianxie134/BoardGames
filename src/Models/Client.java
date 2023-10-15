package Models;

import Factory.GameFactory;
import Game.BoardGame;
import Game.GameType;
import Utilities.Input;

// Control and record the whole game
public class Client {
    private BoardGame game;
    private Player[] players;
    private int round;

    public Client(BoardGame game, Player[] players) {
        this.game = game;
        this.players = players;
        this.round = 1;
    }

    public void start() {
        while (game != null) {
            Player winner = game.start(players);

            result(winner);
            showWinTimes();

            game = ifContinue();
        }
        System.out.println("Welcome next time!");
    }

    public BoardGame ifContinue() {
        System.out.println("Do you want one more Board Games? Type Y/y for continue and N/n for end");
        String continueStatus;
        while (true) {
            continueStatus = Input.wString();
            if (continueStatus.equals("Y") || continueStatus.equals("y")) {
                return GameFactory.buildGame();
            } else if (continueStatus.equals("N") || continueStatus.equals("n")) {
                return null;
            } else {
                System.err.println("Invalid instruction. Please type again.");
            }
        }
    }

    public void result(Player player) {
        if (player != null) {
            System.out.println("Congratulations on Player " + player.getId() + " in round " + round + "!");
            player.win();
        } else if (GameType.getGameName(game).equals("OC")){
            System.out.println("Congratulations on Player 2 in round " + round + "!");
            players[1].win();
        } else {
            System.out.println("It's a draw! Both of you are winners in this game!");
        }
        round += 1;
    }

    public void showWinTimes() {
        System.out.print("Win Times of each player: ");
        for (Player player : players) {
            System.out.print("Player " + player.getId() + " - " + player.getWinTime() + ", ");
        }
        System.out.println();
    }
}
