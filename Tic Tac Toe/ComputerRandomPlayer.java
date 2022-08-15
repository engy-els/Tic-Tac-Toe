//Student 1 name: Engy Elsayed
//Student 2 name: Antonin Francoeur

/**
 * The class <b>ComputerRandomPlayer</b> is the class that controls the computer's plays.
 * 
 * 
 */

import java.util.*;

public class ComputerRandomPlayer implements Player {
	//generate random position at an empty cell!!
	//call game.play(position)

    @Override
    public void play(TicTacToeGame game) {
        if (game.getGameState() != GameState.PLAYING) {
            // Print error message if game is not playable
            System.out.println("The game is not playable, impossible to continue ComputerRandomPlayer.play();");
            return;
        }

        boolean validOptionFound = false;
        do {

            int testIndex = Utils.generator.nextInt(game.getColumns() * game.getLines());
            if(game.valueAt(testIndex) == CellValue.EMPTY) {
               game.play(testIndex);
               validOptionFound = true;
            }
        } while(!validOptionFound);
    }
}