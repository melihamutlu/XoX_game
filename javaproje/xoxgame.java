/**
 * xoxgame
 */

import java.util.Scanner;

public class xoxgame {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try (var in = new Scanner(System.in)) {
            // Players
            System.out.print("Player 1, What is your name?");
            String p1 = in.nextLine();
            System.out.print("Player 2, What is your name?");
            String p2 = in.nextLine();
            
            //3x3 xox game
            //- empty space
            //x player 1
            //y player 2

            char[][] board = new char[3][3];
            
            // Fill the board with dashes
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    board[i][j] = '-';
                }
            }

            //keep track of whose turn it is
      boolean isPlayer1 = true;
            //keep track if the game has ended
      boolean gameEnded = false;

      while(!gameEnded)
      { 
                // Draw the board
                drawBoard(board);

                //Keep track of what symbol we are using to play
                char symbol = ' ';
                if(isPlayer1) {
                    symbol = 'x';
                }else{
                    symbol = 'o';
                }
                //print out the players turn
                if(isPlayer1){
                    System.out.println(p1 + "'s Turn (x):");
                }else{
                    System.out.println(p2 + "'s Turn (o):");
                }

                //row and col variables
                int row = 0;
                int col = 0;

                while(true) {
                        // Get row and col from user
                    System.out.print("Enter a row (0,1, or 2): ");
                    row = in.nextInt();
                    System.out.print("Enter a col (0,1, or 2): ");
                    col = in.nextInt();

                    // Check if row and col are valid
                    if (row < 0 || col < 0 || row > 2 || col > 2){
                        // row and col are out of bounds
                        System.out.println("Your row and col are out of bounds!");
                    }else if(board[row][col] != '-'){
                        //board position has an x or o
                        System.out.println("Someone has already made a move there!");
                    }else{
                        //row and col are valid
                        break;
                    }

                }

                // setting the position on the board to the players symbol
                board[row][col] = symbol;

                //check if a player has won
                if(hasWon(board) == 'x'){
                    //player1 win
                    System.out.println(p1 + " has won! ");
                    gameEnded = true;
                }else if(hasWon(board) == 'o'){
                    //player2 win
                    System.out.println(p2 + " has won! ");
                    gameEnded = true;
                }else{
                    //nobody win
                    if(hasTied(board)){
                        //Tied
                        System.out.println("It is a tie!");
                        gameEnded = true;
                    }else{
                        //continue the game and toggles the turn
                        isPlayer1 = !isPlayer1;
                    }
                }
            }

            //print out final state of the board
            drawBoard(board);
        }
       
    }
    // Printing out the board
    /**
     * @param board
     */
    public static void drawBoard(char[][] board) {

        int i;
        for( i = 0; i < 3; i++) {
           for(int j = 0; j < 3;  j++){
            System.out.print(board[i][j]);
           } 
           System.out.println();
        }
        
    }

    public static char hasWon (char[][] board){
        //row
        for(int i = 0; i < 3; i++){
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-'){
                return board[i][0];
            }
        }
        //col
        for(int j = 0; j < 3; j++){
            if(board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-'){
                return board[0][j];
            }
        }
        //diagonals
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-'){
            return board[0][0];
        }
        if(board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '-'){
            return board[2][0];
        }
        //nobody has win
        return '-';
    }

    // check it the board is full
    public static boolean hasTied(char[][] board){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == '-'){
                    return false;
                }
            }
        }
        return false;
    }

}