/**
 * This is the main File which runs the ConnectFourGame
 * where there is option to play with player or Bot, a
 * non parametric humanPlayer will call the bot to play
 */
public class ConnectFourDriver {
    public static void main(String[] args) {
        ConnectFourPlayerInterface player1 = new HumanPlayer("Bob");
        ConnectFourPlayerInterface player2 = new HumanPlayer();
        ConnectFourGameInterface game = new ConnectFourGame(player1, player2);
        game.playGameWithBot();
        game.getStats();
    }
}
