import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Considered our class that is being inherited from, it takes values from the rest of the program in order to have the proper information
 * to build a level.
 * This class also sends information, especially the Board class so the Board class knows what to paint.
 * @author matthew_duerr
 *
 */
public abstract class Game {
	/**
	 * The cell array is how each and every cell is represented in the game by column and height.
	 */
	private Cell[][] cells;
	/**
	 * Width of board
	 */
	private int width;
	/**
	 * Height of board
	 */
	private int height;
	/**
	 * boolean explaining to program whether or not game has concluded
	 */
	private boolean finished;
	/**
	 *  Creates new Board
	 */
	private Board board;

	public Game(int w, int h){
		width = w;
		height = h;
		cells = new Cell[w][h];
		board = new Board(this);
	}

	/**
	 * The difficulty of the game that we choose from each level will be brought back to this method when called.
	 * @return
	 */
	public abstract int getDifficulty();
	/**
	 * Returns elements regrading board to paint
	 * @return
	 */
	public Board getBoard() {
		return board;
	}
	/**
	 * When called sets board
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	/**
	 * Returns Width when called to paint
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * Sets width of game
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * returns height of board to paint
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * Sets height of game
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * Returns if game is finsihed to paint
	 * @param finished
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	/**
	 * returns cell array to paint
	 * @return 
	 */
	public Cell[][] getCells()
	{
		return cells;
	}
	/**
	 * Resets entire game and brings in new game data in order to play again, including setting mines and numbers
	 */
	public void reset()
	{
		Random random = new Random();
		finished = false;

		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				Cell c = new Cell();
				cells[i][j] = c;
				int r = random.nextInt(100);

				if (r < getDifficulty())
				{
					cells[i][j].setMine();
				}
			}
		}

		setNumbers();
	}
	/**
	 * Practically repaints board, reset places values then refresh resends data to be painted
	 */
	public void refresh()
	{
		board.repaint();
	}

	private void setNumbers()
	{
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				int count = 0;

				if (i > 0 &&	j > 0 && cells[i - 1]	[j - 1]	.Mine()) count++;
				if (j > 0 && cells[i][j - 1].Mine()) count++;
				if (i < width - 1 && j > 0 && cells[i + 1][j - 1].Mine()) count++;

				if (i > 0 && cells[i - 1][j].Mine()) count++;
				if (i < width - 1 && cells[i + 1][j].Mine()) count++;

				if (i > 0 && j < height - 1 && cells[i - 1][j + 1].Mine()) count++;
				if (j < height - 1	&& cells[i] [j + 1].Mine()) count++;
				if (i < width - 1 && j < height - 1 && cells[i + 1][j + 1].Mine()) count++;

				cells[i][j].setNumber(count);

				if (cells[i][j].Mine())
				{
					cells[i][j].setNumber(-1);
				}

				if (cells[i][j].getNumber() == 0)
				{
					cells[i][j].reveal();
				}
			}
		}

		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				if (i > 0 &&	j > 0 && cells[i - 1][j - 1].getNumber() == 0) cells[i][j].reveal();
				if (j > 0 && cells[i][j - 1].getNumber() == 0) cells[i][j].reveal();
				if (i < width - 1 && j > 0 && cells[i + 1][j - 1].getNumber() == 0) cells[i][j].reveal();

				if (i > 0 && cells[i - 1][j].getNumber() == 0) cells[i][j].reveal();
				if (i < width - 1 && cells[i + 1]	[j]		.getNumber() == 0) cells[i][j].reveal();

				if (i > 0 && j < height - 1 && cells[i - 1][j + 1].getNumber() == 0) cells[i][j].reveal();
				if (j < height - 1 && cells[i][j + 1].getNumber() == 0) cells[i][j].reveal();
				if (i < width - 1 && j < height - 1 && cells[i + 1][j + 1]	.getNumber() == 0) cells[i][j].reveal();
			}
		}
	}
	/**
	 * When a cell is selected all information regarding the cell and how it was clicked is gathered, and checks to see if game was lost or won.
	 * @param x column of cell
	 * @param y row of cell
	 */
	public void select(int x, int y)
	{
		if (cells[x][y].Flag()) return;
		cells[x][y].reveal();
		resetMarks();
		refresh();

		if (cells[x][y].Mine())
		{
			lose();
		}
		else if (won())
		{
			win();
		}
	}

	/**
	 * If player loses game the game class prompts the paint method to unflag and reveal every blank as well as finishing game and displays message for
	 * player. Then automatically resets board without refreshing.
	 */
	private void lose()
	{
		finished = true;
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				if (!cells[i][j].Blank()) cells[i][j].unflag();
				cells[i][j].reveal();
			}
		}

		refresh();
		JOptionPane.showMessageDialog(null, "LOUD NOISES! Sorry try again!");
		reset();
	}

	/**
	 * If player wins it ends game, prompts player that they won and unflags and reveals board, player wins if all mines are found
	 */
	private void win()
	{
		finished = true;
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				cells[i][j].reveal();
				if (!cells[i][j].Mine()) cells[i][j].unflag();
			}
		}

		refresh();
		JOptionPane.showMessageDialog(null, "You actually won! WOW!");
		reset();
	}
	/**
	 * If all cells are found whether or not there are blanks then game is finshed
	 * @return
	 */
	private boolean won()
	{
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				if (cells[i][j].Blank() && !cells[i][j].Mine())
				{
					return false;
				}
			}
		}

		return true;
	}
	/**
	 * Flag operations, if and reverses flag 
	 * @param x
	 * @param y
	 */
	public void mark(int x, int y)
	{
		if (cells[x][y].Flag()) cells[x][y].unflag();
		else if (cells[x][y].Blank()) cells[x][y].flag();

		resetMarks();
	}
	/**
	 * if already a flag and left click then remove flag
	 */
	private void resetMarks()
	{
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				if (!cells[i][j].Blank()) cells[i][j].unflag();
			}
		}
	}
	/**
	 * simple boolean stating whether or not the game is finished, if boolean is swapped then the game is always finished.
	 * @return
	 */
	public boolean isFinished()
	{
		return finished;
	}


}
