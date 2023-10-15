package Game;

import Models.Player;

public interface Game {
    void welcome();

    default Player start(Player[] players) {
        welcome();

        return process(players);
    }

    Player process(Player[] players);

    Player judge(Player[] players, int turn);
}
