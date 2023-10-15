package Game;

// Store all the game types, convenient for game selection
public enum GameType {
    TTT("Tic Tac Toe", "Tic Tac Toe game..."),
    OC("Order Chaos", "Order Chaos game..."),
    STTT("Super Tic Tac Toe", "Super Tic Tac Toe game...");

    private final String name;
    private final String description;

    GameType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static void gameChoices() {
        System.out.println("Please select the game you want to play:");
        for (GameType type: values()) {
            System.out.printf("%d. %s\n", (type.ordinal()+1), type.name);
        }
    }

    // search if we offer a game for the input number
    public static GameType searchGame(int i) {
        for (GameType value : values()) {
            if (value.ordinal() == i-1) {
                return value;
            }
        }
        System.err.println("Not found this game. Please select again.");
        return null;
    }

    public static String getGameName(Game game) {
        return game.getClass().getSimpleName();
    }
}
