/**
 * interface for the human player features,
 * contains methods for human as well as bot
 */
interface ConnectFourPlayerInterface
{
    int takeTurn();
    String getName();
    int getNumberOfWins();
    void addWin();
    char getGamePiece();
    void setGamePiece(char gamePiece);
    void setPlayerNumber(int num);
    String getBotName();
}