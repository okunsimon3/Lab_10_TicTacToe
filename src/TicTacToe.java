import java.util.Scanner;

public class TicTacToe {
    //Class level declarations
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];

    private static int moveCnt = 0;




    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String player = "";
        boolean done = false;

        do {
            //clearing the board at the start of every game
            clearBoard();
            //Set player to X (X always moves first
            player = "X";
            //reset move counter
            moveCnt = 0;

            //inner game loop
            do {
                //Shows the board (clear at the start of every game)
                showBoard();
                //Get player move using getRangedInt (1 - 3) and convert the player move to the array by subtracting 1
                int row = (SafeInput.getRangedInt(in, "Player " + player + ", enter which row you will choose to put your move ",1, 3) - 1);
                int col = (SafeInput.getRangedInt(in, "Player " + player + ", enter which column you choose to put your move ",1, 3) - 1);

                //check for valid move
                if(isValidMove(row, col)) {
                    //player move coordinates gets put into array
                    board[row][col] = player;
                    //move counter increases by 1
                    moveCnt = moveCnt + 1;
                    //displays move counter
                    System.out.println("\n" + "Move number: " + moveCnt);


                    //check for win or tie
                    if(isWin(player)) {
                        System.out.println("Player " + player + " wins!");
                        done = true;
                    } else if (isTie()) {
                        System.out.println("It's a tie!");
                        done = true;
                    } else {
                        //Switches players at the end of the loop
                        if (player.equals("X")) {
                            player = "O";
                        } else {
                            player = "X";
                        }
                    }

                } else {
                    //Displays to the user that they made an invalid move
                    System.out.println("You entered an invalid move. Try again!");
                }
            }while(!done);
            //Shows board at the end of the game
            showBoard();
            //Prompts user if they want to play again
            done = SafeInput.getYNConfirm(in, "Would you like to play again [Y or N]: ");
        }while(!done);
        System.out.println("Thank you for playing!");

    }


    private static void clearBoard() { //Set all the board elements to a space
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                board[row][col] = "";
            }
        }
    }

    private static void showBoard() { //Shows the board so players can choose where to put their moves
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                System.out.print(" | " + board[row][col]);
            }
            System.out.println(" |");
        }
    }
    private static boolean isValidMove(int row, int col) { //returns true if there is a space at the given move coordinates (legal move)
        return board[row][col].equals("");
    }
    private static boolean isWin(String player) {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player)) {
            return true;
        }
        return false;
    }
    private static boolean isColWin(String player) { //checks for a column win for specified player
        for(int col = 0; col < COL; col++) {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isRowWin(String player) { //checks for a row win for specified player
        for(int row = 0; row < ROW; row++) {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagnalWin(String player) { //checks for a diagonal win for specified player
        if((board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) || (board[2][0].equals(player) && board[1][1].equals(player) && board[0][2].equals(player))) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean isTie() { //checks for a tie
        if(moveCnt == 9) {
            return true;
        } else {
            return false;
        }
    }
}