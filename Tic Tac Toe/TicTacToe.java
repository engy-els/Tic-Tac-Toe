//Student 1 name: Engy Elsayed
//Student 2 name: Antonin Francoeur

import java.io.Console;
import java.util.Scanner;

/**
 * The class <b>TicTacToe</b> is the class that implements the actual Tic Tac
 * Toe game, where it
 * controls the human and computer activity and prints the result of the game at
 * the end. It also
 * asks the player if he/she wants to continue playing once this game is over.
 * 
 * 
 */

public class TicTacToe {

    /**
     * <b>main</b> of the application. Creates the instance of GameController
     * and starts the game. If two parameters line and column
     * are passed, they are used.
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (less than 2).
     * 
     * @param args
     *             command line parameters
     */
    public static void main(String[] args) {
        StudentInfo.display();

        int lines = 3;
        int columns = 3;
        int win = 3;

        if (args.length >= 2) {
            lines = Integer.parseInt(args[0]);
            if(lines < 2){
                System.out.println("Invalid argument, using default...");
                lines = 3;
            }
            columns = Integer.parseInt(args[1]);
            if(columns < 2){
                System.out.println("Invalid argument, using default...");
                columns = 3;
            }
        }
        if (args.length == 3){
            // The third argument will be ignored and 3 will be used
            win = 3; //for simplcity, we will only consider the case of 3 cells to win
        }
        if (args.length > 3){
            System.out.println("Too many arguments. Only the first 3 are used.");
        }


		//define an array (say p) of two players (use interface playe for the refernce)
		// The first playe is an object of type HumanPlayer and 
		// the second player is an object of type  ComputerRandomPlayer()

        Player p[] = new Player[]{
            new HumanPlayer(),
            new ComputerRandomPlayer()
        };

		//choose player randomly (p[0] or p[1]) 
		
		// create a reference to an object of type TicTacToeGame



		// loop until the input is not 'y'
        Scanner scanner = new Scanner(System.in);
        int currentPlayer = Utils.generator.nextInt(2);
		do {
            // create object for TicTacToeGame
            TicTacToeGame game = new TicTacToeGame(lines, columns, win);

            // for loop that prints who's turn it is, the board, and who is to play, until
            // the game ends
            while(game.getGameState() == GameState.PLAYING) {

                System.out.println("Player " + (currentPlayer + 1));
                p[currentPlayer].play(game);


                //flip current player
                currentPlayer = 1 - currentPlayer;
            }

            System.out.println("Game over");
            System.out.print(game.toString());

            // prints result of game and ask if you want to play again
            if(game.getGameState() == GameState.DRAW) {
                System.out.println("Result: DRAW");
            } else if(game.getGameState() == GameState.OWIN) {
                System.out.println("Result: OWIN");
            } else if(game.getGameState() == GameState.XWIN) {
                System.out.println("Result: XWIN");
            }

            System.out.println("Play again (Y)?:");
		} while(Character.toLowerCase(scanner.next().charAt(0)) == 'y');
    }
}