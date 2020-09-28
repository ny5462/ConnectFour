/**
 * This class Human player  implements the ConnectFourPlayerInterface,
 * contains the substance for human player as well as bot names
 * when no parameter is assigned
 */
public class HumanPlayer implements ConnectFourPlayerInterface {
    String name;
    String botname;
    int playerNo;
    char gamePiece;
    static boolean playerbot = false;
    public int wins = 0;
    static int turnOfPlayer = 1;

    //Class constructor with name as parameter
    public HumanPlayer(String name) {
        this.name = name;
    }

    // Constructor for bot
    public HumanPlayer() {
        this.playerbot = true;
        this.botname = "Group 11 alpha version";
    }

    /**
     * Switches turns between players
     *
     * @return player turn
     */
    @Override
    public int takeTurn() {
        if (turnOfPlayer == 1) {
            turnOfPlayer = 2;
        } else if (turnOfPlayer == 2) {
            turnOfPlayer = 1;
        }
        return turnOfPlayer;
    }

    /**
     * returns name of player
     *
     * @return PlayerName
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * gives Number of wins
     *
     * @return win count
     */
    @Override
    public int getNumberOfWins() {
        return this.wins;
    }

    /**
     * Adds win to a player
     */
    @Override
    public void addWin() {
        System.out.println("adding a victory to " + this.name);
        this.wins++;
    }

    /**
     * gives game piece of player
     *
     * @return char game piece
     */
    @Override
    public char getGamePiece() {
        return this.gamePiece;
    }

    /**
     * sets the game piece for a player
     *
     * @param gamePiece char to set for player
     */
    @Override
    public void setGamePiece(char gamePiece) {
        this.gamePiece = gamePiece;
        System.out.println(this.name + " Is assigned the game piece " + this.gamePiece);
    }

    /**
     * sets no of player
     *
     * @param num int no
     */
    @Override
    public void setPlayerNumber(int num) {
        this.playerNo = num;
        System.out.println(this.name + " Is assigned " + this.playerNo);

    }

    /**
     * returns name of the bot
     *
     * @return bot name
     */
    @Override
    public String getBotName() {
        return this.botname;
    }
}
