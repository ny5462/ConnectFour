/*
 * ConnectFourGame.java
 * @author : Nikhil Yadav
 * @author : Harshal Bendale
 *
 * Version- 1.0
 *
 * filename : ConnectFourGame.java
 *
 * This is a class file for Connect Four Game.
 * This drives the Connect Four Game. It inherits from the
 * Human player class and implements connect four game Interface
 *
 */


/**
 * importing necessary libraries for input
 * and pattern matching to check for victory
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This is a Connect Four Game class file.
 * This sub-class of Human Player class and connects to interface Connect Four Game.
 */
public class ConnectFourGame extends HumanPlayer implements ConnectFourGameInterface {
    static Scanner sc = new Scanner(System.in);
    static int winnerNo;
    static char[][] board = new char[6][7];
    static int prev_playerColumn;
    static boolean playerBot = false;
    static int current_playerColumn;
    static boolean firstmove = true;
    static boolean player1FirstMove = true;
    ConnectFourPlayerInterface player1, player2;


    /*
     * Constructor for Connect Four players.
     */
    public ConnectFourGame(ConnectFourPlayerInterface player1, ConnectFourPlayerInterface player2) {
        this.player1 = player1;
        this.player2 = player2;
    }


    /*
     * This refreshes the board for new game.
     * Resets the board for new game.
     */
    private static char[][] refreshBoard() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = '.';
            }
        }

        return board;
    }


    /*
     * This displays the board for current board.
     * After taking turn this method is called to display current situation of the game.
     */
    private static void showGame() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }


    /*
     * This method determines if the player has won the game.
     * Returns true if the player has won the game.
     */
    public boolean checkForWin() {
        boolean checkWin = false;
        if (string2CheckHorizontal(this.board, 6, 7)) {
            checkWin = string2CheckHorizontal(this.board, 6, 7);
            winnerNo = this.playerNo;
            if (turnOfPlayer == 1)
                System.out.println("You won! " + player1.getName());
            else if (turnOfPlayer == 2)
                if (playerBot)
                    System.out.println("You won! " + player2.getBotName());
                else
                    System.out.println("You won! " + player2.getName());
        } else if (string2CheckVertical(this.board, 6, 7)) {
            checkWin = string2CheckVertical(board, 6, 7);
            winnerNo = this.playerNo;
            if (turnOfPlayer == 1)
                System.out.println("You won! " + player1.getName());
            else if (turnOfPlayer == 2)
                if (playerBot)
                    System.out.println("You won! " + player2.getBotName());
                else
                    System.out.println("You won! " + player2.getName());

        } else if (string2CheckDiagonal(this.board, 6, 7)) {
            checkWin = string2CheckDiagonal(this.board, 6, 7);
            name = this.getName();
            winnerNo = this.playerNo;
            if (turnOfPlayer == 1)
                System.out.println("You won! " + player1.getName());
            else if (turnOfPlayer == 2)
                if (playerBot)
                    System.out.println("You won! " + player2.getBotName());
                else
                    System.out.println("You won! " + player2.getName());

        }
        return checkWin;
    }


    /*
     * Generates regex string that is used for checking wins of the respective player.
     */
    private String getRegex() {
        String checkString = "";
        if (turnOfPlayer == 1) {
            checkString = checkString + player1.getGamePiece() + player1.getGamePiece() + player1.getGamePiece() + player1.getGamePiece();

        } else {
            checkString = checkString + player2.getGamePiece() + player2.getGamePiece() + player2.getGamePiece() + player2.getGamePiece();

        }
        return checkString;
    }

    /*
     * This is Regular Expression pattern matcher.
     * Here the regEx pattern is declared which checks with the matcher.
     */
    private boolean calltoCheck(String line, String checker) {


        // Create a Pattern object
        String regex = "^.*(" + checker + ").*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        boolean test = matcher.find();

        return test;
    }


    /*
     * This method finds the string in horizontal manner.
     *
     */
    private boolean string2CheckHorizontal(char[][] puzzle, int row, int column) {


        boolean flagFound = false;
        String str2CheckHorizontal = "";


        //Horizontal
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                str2CheckHorizontal = str2CheckHorizontal + puzzle[i][j];
            }
            if (calltoCheck(str2CheckHorizontal, getRegex())) {
                flagFound = true;
                //System.out.println("4 symbols found in horizontal and you've won!");
                break;
            }


            str2CheckHorizontal = "";
        }
        return flagFound;
    }

    private boolean string2CheckVertical(char[][] puzzle, int row, int column) {
        /*
         * This method finds the string in vertical manner.
         *
         */
        String str2CheckVertical = "";
        boolean flagFound = false;


        //Vertical
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                str2CheckVertical = str2CheckVertical + puzzle[j][i];
            }
            if (calltoCheck(str2CheckVertical, getRegex())) {
                flagFound = true;
                //System.out.println("4 symbols found in vertical and you've won!");
                break;
            }
            str2CheckVertical = "";
        }
        return flagFound;
    }

    /**
     * This method finds the string in diagonal manner.
     *
     * @param puzzle
     * @param row
     * @param column
     * @return
     */
    private boolean string2CheckDiagonal(char[][] puzzle, int row, int column) {

        boolean flagFound = false;

        String str2CheckDiagonal = "";

        //Top left diagonals
        for (int k = 0; k <= row - 1; k++) {
            int i = k;
            int j = 0;
            while (i >= 0) {
                str2CheckDiagonal = str2CheckDiagonal + puzzle[i][j];
                i--;
                j++;
            }
            if (calltoCheck(str2CheckDiagonal, getRegex())) {
                flagFound = true;
                //System.out.print("1. The word is found in diagonal position.");
                break;
            }
            str2CheckDiagonal = "";
        }

        //Bottom right diagonals
        for (int k = 1; k <= column; k++) {
            int i = row - 1;
            int j = k;
            while (j <= column - 1) {
                str2CheckDiagonal = str2CheckDiagonal + puzzle[i][j];
                i--;
                j++;
            }
            if (calltoCheck(str2CheckDiagonal, getRegex())) {
                flagFound = true;
                //System.out.print("2. The word is found in diagonal position.");
                break;
            }
            str2CheckDiagonal = "";

        }

        //Upper right diagonals.
        for (int i = 0; i <= 6; i++) {
            for (int j = 0, k = i; j <= 5 && k <= 6; j++, k++) {
                str2CheckDiagonal = str2CheckDiagonal + puzzle[j][k];
            }
            if (calltoCheck(str2CheckDiagonal, getRegex())) {
                flagFound = true;
                //System.out.print("3. The word is found in diagonal position.");
                break;
            }
            str2CheckDiagonal = "";

        }

        //Lower left diagonals.
        for (int i = 0; i <= 5; i++) {
            for (int j = 0, k = i; j <= 5 && k <= 5; j++, k++) {
                str2CheckDiagonal = str2CheckDiagonal + puzzle[k][j];
            }
            if (calltoCheck(str2CheckDiagonal, getRegex())) {
                flagFound = true;
                //System.out.print("4. The word is found in diagonal position.");
                break;
            }
            str2CheckDiagonal = "";
        }

        return flagFound;
    }


    /**
     * This method devises a strategic column for the bot
     * and inserts it into the board
     */
    public void botStrategy() {
        int col_Strategy;
        if (firstmove) {
            firstmove = false;
            col_Strategy = 3;
        } else if (current_playerColumn == prev_playerColumn) {
            if (board[0][current_playerColumn] == '.')
                col_Strategy = current_playerColumn;
            else if (current_playerColumn == 0)
                col_Strategy = current_playerColumn + 1;
            else if (current_playerColumn == 6)
                col_Strategy = current_playerColumn - 1;
            else
                col_Strategy = current_playerColumn + 1;

        } else if (current_playerColumn >= 3 && current_playerColumn < 6 && current_playerColumn != prev_playerColumn) {
            if (board[0][current_playerColumn + 1] == '.')
                col_Strategy = current_playerColumn + 1;
            else if (current_playerColumn + 1 == 6)
                col_Strategy = current_playerColumn - 1;
            else
                col_Strategy = current_playerColumn - 1;

        } else if (current_playerColumn < 3 && current_playerColumn > 0 && current_playerColumn != prev_playerColumn) {
            if (board[0][current_playerColumn - 1] == '.')
                col_Strategy = current_playerColumn - 1;
            else
                col_Strategy = current_playerColumn;
        } else if (current_playerColumn == 0 && current_playerColumn != prev_playerColumn) {
            if (board[0][current_playerColumn + 1] == '.')
                col_Strategy = current_playerColumn + 1;
            else
                col_Strategy = current_playerColumn;
        } else if (board[0][6] == '.')
            col_Strategy = current_playerColumn - 1;
        else
            col_Strategy = current_playerColumn;

        int i = 5;
        while (i >= 0 && i < 6) {
            if (this.board[i][col_Strategy] == '.') {
                System.out.println("Chance of " + player2.getName());
                this.board[i][col_Strategy] = player2.getGamePiece();
                break;
            } else {
                //System.out.println("Invalid entry, there exists a piece in that slot");
            }
            i--;
        }
        showGame();
    }


    /**
     * This method inserts the piece in board for human
     * players
     *
     * @param symbol game piece
     */
    private void insertInBoard(char symbol) {
        if (turnOfPlayer == 1) {
            System.out.println("Enter the column where you want to insert your game piece (0-6) " + player1.getName());
        } else {
            System.out.println("Enter the column where you want to insert your game piece (0-6) " + player2.getName());
        }
        int col = sc.nextInt();
        while (col >= 7 || col <= -1) {
            System.out.println("enter correct column,exceeds limits");
            col = sc.nextInt();
        }
        if (player1FirstMove) {
            prev_playerColumn = col;
            current_playerColumn = col;
            player1FirstMove = false;
        }
        if (player1FirstMove == false) {
            prev_playerColumn = current_playerColumn;
            current_playerColumn = col;
        }

        int i = 5;

        //Checking if whole column is full.
        while ((board[0][col] != '.')) {
            System.out.println("Invalid entry as the column is full or exceeds limits. Play again. ");
            System.out.println("Enter the column where you want to insert your game piece (0-6)");
            col = sc.nextInt();
        }
        //Toggles the player turns.
        if (turnOfPlayer == 1) {
            while (i >= 0 && i < 6) {
                if (this.board[i][col] == '.') {
                    System.out.println("Chance of " + player1.getName());
                    this.board[i][col] = player1.getGamePiece();
                    break;
                } else if (i < 0) {
                    //System.out.println("Invalid entry, there exists a piece in that slot");
                }
                i--;
            }
            showGame();

        } else if (turnOfPlayer == 2) {
            while (i >= 0 && i < 6) {
                if (this.board[i][col] == '.') {
                    System.out.println("Chance of " + player2.getName());
                    this.board[i][col] = player2.getGamePiece();
                    break;
                } else {
                    //System.out.println("Invalid entry, there exists a piece in that slot");
                }
                i--;
            }
            showGame();
        }
    }


    /*
     * Fetches the Win stats of the players and displays the result.
     */
    @Override
    public void getStats() {
        System.out.println("Next Round begins now");
        System.out.println("Player 1 wins :" + player1.getNumberOfWins());
        System.out.println("Player 2 wins :" + player2.getNumberOfWins());
    }


    /*
     * The main driver for the game.
     */
    @Override
    public void playGame() {
        refreshBoard();
        System.out.println("Enter Game piece for player 1 " + player1.getName());
        char piece1 = sc.next().charAt(0);
        player1.setGamePiece(piece1);

        System.out.println("Enter game piece for player 2 " + player2.getName());
        char piece2 = sc.next().charAt(0);
        player2.setGamePiece(piece2);

        while (true) {
            if (turnOfPlayer == 1) {
                insertInBoard(piece1);
                if (checkForWin()) {
                    player1.addWin();
                    System.out.println("Do you want to play again? (Y/N)");
                    String decision = sc.next();
                    if (decision.equals("Y") || decision.equals("y")) {
                        getStats();
                        refreshBoard();
                        System.out.println("Enter Game piece for player 1 " + player1.getName());
                        piece1 = sc.next().charAt(0);
                        player1.setGamePiece(piece1);
                        System.out.println("Enter game piece for player 2 " + player2.getName());
                        piece2 = sc.next().charAt(0);
                        player2.setGamePiece(piece2);
                    } else if (decision.equals("N") || decision.equals("n")) {
                        System.out.println("Exiting game ");
                        getStats();
                        System.exit(1);
                    }
                }

            }
            if (turnOfPlayer == 2) {
                insertInBoard(piece2);
                if (checkForWin()) {
                    player2.addWin();
                    System.out.println("Do you want to play again? (Y/N)");
                    String decision = sc.next();
                    if (decision.equals("Y") || decision.equals("y")) {
                        getStats();
                        refreshBoard();
                    } else if (decision.equals("N") || decision.equals("n")) {
                        System.out.println("Exiting game ");
                        getStats();
                        System.exit(1);
                    }
                }
            }
            takeTurn();
        }
    }

    /**
     * Method is called when a human plays with a bot
     * with bot being player 2
     */
    public void playGameWithBot() {
        refreshBoard();
        System.out.println("Enter Game piece for player 1 " + player1.getName());
        char piece1 = sc.next().charAt(0);
        player1.setGamePiece(piece1);

        System.out.println("Enter game piece for player 2 " + player2.getName());
        char piece2 = sc.next().charAt(0);
        player2.setGamePiece(piece2);

        while (true) {
            if (turnOfPlayer == 1) {
                insertInBoard(piece1);
                if (checkForWin()) {
                    player1.addWin();
                    System.out.println("Do you want to play again? (Y/N)");
                    String decision = sc.next();
                    if (decision.equals("Y") || decision.equals("y")) {
                        getStats();
                        refreshBoard();
                        System.out.println("Enter Game piece for player 1 " + player1.getName());
                        piece1 = sc.next().charAt(0);
                        player1.setGamePiece(piece1);
                        System.out.println("Enter game piece for player 2 " + player2.getName());
                        piece2 = sc.next().charAt(0);
                        player2.setGamePiece(piece2);
                    } else if (decision.equals("N") || decision.equals("n")) {
                        System.out.println("Exiting game ");
                        getStats();
                        System.exit(1);
                    }
                }

            }
            if (turnOfPlayer == 2) {
                botStrategy();
                if (checkForWin()) {
                    player2.addWin();
                    System.out.println("Do you want to play again? (Y/N)");
                    String decision = sc.next();
                    if (decision.equals("Y") || decision.equals("y")) {
                        getStats();
                        refreshBoard();
                    } else if (decision.equals("N") || decision.equals("n")) {
                        System.out.println("Exiting game ");
                        getStats();
                        System.exit(1);
                    }
                }
            }
            takeTurn();
        }
    }


}

