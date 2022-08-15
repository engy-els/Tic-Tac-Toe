//Student 1 name: Engy Elsayed
//Student 2 name: Antonin Francoeur

import java.util.Scanner;

/**
 * The class <b>HumanPlayer</b> is the class that controls the human's plays.
 * 
 * 
 */

public class HumanPlayer implements Player {
	//read a position to play from the console and call 
	// game.play(position): if the level was advanced after the call, then finish, otherwise repeat and get another position

    @Override
    public void play(TicTacToeGame game) {
        if(game.getGameState() != GameState.PLAYING) {
            // Print error message if game is not playable
            System.out.println("The game is not playable, impossible to continue HumanPlayer.play();");
            return;
        }

        System.out.print(game.toString());

        // Ask for valid input, if not valid ask again
        Scanner scanner = new Scanner(System.in);
        if(game.nextCellValue() == CellValue.X) {
            System.out.print("X to play: ");
        } else {
            System.out.print("O to play: ");
        }
        while(true) {
            int index = scanner.nextInt() - 1;
            if (game.play(index)) {
                return;
            }
        }
    }
}