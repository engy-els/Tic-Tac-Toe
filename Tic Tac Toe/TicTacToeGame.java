//Student 1 name: Engy Elsayed
//Student 2 name: Antonin Francoeur
/**
 * The class <b>TicTacToeGame</b> is the class that implements the Tic Tac Toe
 * Game. It contains the grid and tracks its progress. It automatically maintain
 * the current state of the game as players are making moves.
 *
 * 
 */

public class TicTacToeGame {

	/**
	 * The board of the game, stored as a single array.
	 */
	private CellValue[] board;


	/**
	 * level records the number of rounds that have been
	 * played so far. Starts at 0.
	 */
	private int level;

	/**
	 * gameState records the current state of the game.
	 */
	private GameState gameState = GameState.PLAYING;


	/**
	 * lines is the number of lines in the grid
	 */
	private int lines;

	/**
	 * columns is the number of columns in the grid
	 */
	private int columns;


	/**
	 * sizeWin is the number of cell of the same type
	 * that must be aligned to win the game. 
	 * For simplicity, it will be always 3 in this assignment.
	 */
	private int sizeWin;

	/**
	 * The value of the cell that the player in the next turn is going to us.
	 */
	private CellValue nextTurnCellValue = CellValue.X;

	/**
	 * default constructor, for a game of 3x3, which must
	 * align 3 cells
	 */
	public TicTacToeGame(){
		board = new CellValue[3 * 3];
		for(int i = 0; i < board.length; i++) {
			board[i] = CellValue.EMPTY;
		}
		lines = 3;
		columns = 3;
		sizeWin = 3;
	}

	/**
	 * constructor allowing to specify the number of lines
	 * and the number of columns for the game. 3 cells must
	 * be aligned.
	 * @param lines
	 *  the number of lines in the game
	 * @param columns
	 *  the number of columns in the game
	 */
	public TicTacToeGame(int lines, int columns){
		board = new CellValue[lines * columns];
		for(int i = 0; i < board.length; i++) {
			board[i] = CellValue.EMPTY;
		}
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = 3;
	}

	/**
	 * constructor allowing to specify the number of lines
	 * and the number of columns for the game, as well as
	 * the number of cells that must be aligned to win.
	 * @param lines
	 *  the number of lines in the game
	 * @param columns
	 *  the number of columns in the game
	 * @param sizeWin
	 *  the number of cells that must be aligned to win.
	 */
	public TicTacToeGame(int lines, int columns, int sizeWin){
		board = new CellValue[lines * columns];
		for(int i = 0; i < board.length; i++) {
			board[i] = CellValue.EMPTY;
		}
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = sizeWin;
	}



	/**
	 * getter for the variable lines
	 * @return
	 * 	the value of lines
	 */
	public int getLines(){
		return lines;
	}

	/**
	 * getter for the variable columns
	 * @return
	 * 	the value of columns
	 */
	public int getColumns(){
		return columns;
	}

	/**
	 * getter for the variable level
	 * @return
	 * 	the value of level
	 */
	public int getLevel(){
		return level;
	}


	/**
	 * getter for the variable gameState
	 * @return
	 * 	the value of gameState
	 */
	public GameState getGameState(){
		return gameState;
	}

	/**
	 * getter for the variable sizeWin
	 * @return
	 * 	the value of sizeWin
	 */
	public int getSizeWin(){
		return sizeWin;
	}

	/**
	 * returns the cellValue that is expected next,
	 * in other word, which played (X or O) should
	 * play next.
	 * This method does not modify the state of the
	 * game.
	 * @return
	 *  the value of the enum CellValue corresponding
	 * to the next expected value.
	 */
	public CellValue nextCellValue(){
		return nextTurnCellValue;
	}

	/**
	 * returns the value  of the cell at
	 * index i.
	 * If the index is invalid, an error message is
	 * printed out. The behaviour is then unspecified
	 * @param i
	 *  the index of the cell in the array board
	 * @return
	 *  the value at index i in the variable board.
	 */
	public CellValue valueAt(int i) {
		return board[i];
	}

	/**
	 * This method is called by the next player to play
	 * at the cell  at index i.
	 * If the index is invalid, an error message is
	 * printed out. The behaviour is then unspecified
	 * If the chosen cell is not empty, an error message is
	 * printed out. The behaviour is then unspecified
	 * If the move is valide, the board is updated, as well
	 * as the state of the game.
	 * To faciliate testing, it is acceptable to keep playing
	 * after a game is already won. If that is the case, the
	 * a message should be printed out and the move recorded.
	 * the winner of the game is the player who won first
	 * @param i
	 *  the index of the cell in the array board that has been
	 * selected by the next player
	 */
	public boolean play(int i) {
		if(i < 0 || i >= board.length) {
			System.out.println("The value should be between 1 and " + (board.length));
			System.out.print(toString());
			System.out.print("Try again: ");
			return false;
		}
		if(board[i] != CellValue.EMPTY) {
			System.out.println("This cell has already been played.");
			System.out.print(toString());
			System.out.print("Try again: ");
			return false;
		}
		board[i] = nextTurnCellValue;
		if(nextTurnCellValue == CellValue.X)
			nextTurnCellValue = CellValue.O;
		else
			nextTurnCellValue = CellValue.X;

		setGameState(i);
		level++;
		return true;
	}

	/**
	 * Gets a cell value at a given x, y coordinates
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return The cell value in board at (x, y)
	 */
	private CellValue getValueAt(int x, int y) {
		return board[x + y * columns];
	}

	/**
	 * Changes the win game state to checkValue if the win flag is on and if nobody already won.
	 * @param mightWin The win flag.
	 * @param checkValue Who should be the winner.
	 * @return Returns true if someone won.
	 */
	private boolean tryWin (boolean mightWin, CellValue checkValue) {
		if(mightWin && checkValue == CellValue.X) {
			if(gameState == GameState.PLAYING) {
				gameState = GameState.XWIN;
			}
			return true;
		} else if (mightWin && checkValue == CellValue.O) {
			if(gameState == GameState.PLAYING) {
				gameState = GameState.OWIN;
			}
			return true;
		}
		return false;
	}
	/**
	 * A helper method which updates the gameState variable
	 * correctly after the cell at index i was just set.
	 * The method assumes that prior to setting the cell
	 * at index i, the gameState variable was correctly set.
	 * it also assumes that it is only called if the game was
	 * not already finished when the cell at index i was played
	 * (the the game was playing). Therefore, it only needs to
	 * check if playing at index i has concluded the game
	 * So check if 3 cells are formed to win.
	 //  * @param i
	 *  the index of the cell in the array board that has just
	 * been set
	 */
	private void setGameState(int index){
		CellValue recentPlayerValue = board[index];

		// Check for rows
		int rowX = columns - sizeWin + 1;
		int rowY = lines;
		for(int x = 0; x < rowX; x++) {
			for(int y = 0; y < rowY; y++) {
				boolean win = true;
				CellValue checkValue = getValueAt(x, y);
				for(int i = 1; i < sizeWin; i++) {
					if(getValueAt(x + i, y) != checkValue) {
						win = false;
						break;
					}
				}

				if(tryWin(win, checkValue)) return;
			}
		}

		// Check for columns
		int colX = columns;
		int colY = lines - sizeWin + 1;
		for(int x = 0; x < colX; x++) {
			for(int y = 0; y < colY; y++) {
				boolean win = true;
				CellValue checkValue = getValueAt(x, y);
				for(int i = 1; i < sizeWin; i++) {
					if(getValueAt(x,y + i) != checkValue) {
						win = false;
						break;
					}
				}

				if(tryWin(win, checkValue)) return;
			}
		}

		// Check for diagonals \
		int diagX = columns - sizeWin + 1;
		int diagY = lines - sizeWin + 1;
		for(int x = 0; x < diagX; x++) {
			for(int y = 0; y < diagY; y++) {
				boolean win = true;
				CellValue checkValue = getValueAt(x, y);
				for(int i = 1; i < sizeWin; i++) {
					if(getValueAt(x + i,y + i) != checkValue) {
						win = false;
						break;
					}
				}

				if(tryWin(win, checkValue)) return;
			}
		}

		// Check for diagonals \
		diagX = columns - sizeWin + 1;
		diagY = lines - sizeWin + 1;
		for(int x = 0; x < diagX; x++) {
			for(int y = 0; y < diagY; y++) {
				boolean win = true;
				CellValue checkValue = getValueAt(x + sizeWin - 1, y);
				for(int i = 1; i < sizeWin; i++) {
					if(getValueAt(x + sizeWin - i - 1,y + i) != checkValue) {
						win = false;
						break;
					}
				}

				if(tryWin(win, checkValue)) return;
			}
		}

		// It's a draw if all the cells are NOT empty
		boolean allCellOccupied = true;
		for(int i = 0; i < board.length; i++) {
			if(board[i] == CellValue.EMPTY) allCellOccupied = false;
		}
		if(allCellOccupied) {
			gameState = GameState.DRAW;
			return;
		}

		// Else, we continue
		gameState = GameState.PLAYING;
	}


	final String NEW_LINE = System.getProperty("line.separator");
	// returns the OS dependent line separator

	/**
	 * Returns a String representation of the game matching
	 * the example provided in the assignment's description
	 *
	 * @return
	 *  String representation of the game
	 */
	public String toString() {
		String result = new String();
		for(int y = 0; y < lines; y++) {
			for(int x = 0; x < columns; x++) {

				// Writes characters corresponding to the current enum case
				switch (board[x + y * columns]) {
					case EMPTY: result += "  "; break;
					case O: result += " O"; break;
					case X: result += " X"; break;
				}

				// Add a divider except for the last columns
				if(x != columns - 1)
					result += " |";
			}
			result += NEW_LINE;

			// Add a divider except for the last row
			if(y != lines - 1) {
				int numDashes = (4 * columns) - 1;
				for (int i = 0; i < numDashes; i++) {
					result += "-";
				}
				result += NEW_LINE;
			}
		}
		return result;
	}

}