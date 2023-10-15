package Factory;

import Game.BoardGame;
import Game.GameType;
import Models.Client;
import Models.Piece;
import Models.Player;
import Utilities.Input;

// Build a Client for Gaming which contains Game and Player
public class ClientFactory {
    public static Client buildClient() {
        BoardGame game = GameFactory.buildGame();

        Player[] players = createPlayers(GameType.getGameName(game));

        return new Client(game, players);
    }

    public static Player[] createPlayers(String game) {
        int playerNum = 2;
        System.out.println("We will have " + playerNum + " players in this game.");
        Player[] players = new Player[playerNum];

        switch (game) {
            case "TTT":
            case "STTT":
                System.out.println("The first player can choose a mark now: (better not be numbers)");
                String mark1 = Input.wString();
                players[0] = new Player(new Piece(mark1), 1); // player 1

                System.out.println("And the other one's mark:");
                String mark2;
                while (true) {
                    mark2 = Input.wString();
                    if (mark2.equals(mark1)) {
                        System.out.println("You can't have the same mark with the other player. Please choose again.");
                    } else {
                        break;
                    }
                }
                players[1] = new Player(new Piece(mark2), 2);

                System.out.println("Marks are '" + players[0].getMark() + "' for player 1 and '" +
                        players[1].getMark() + "' for player 2");
                return players;

            case "OC":
                players[0] = new Player(1); // player 1
                players[1] = new Player(2); // player 1
                return players;

            default:
                throw new RuntimeException();
        }
    }
}
