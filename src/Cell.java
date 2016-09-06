
/**
 * Used to understand the state of each cell, since a cell can contain a mine, be showing or flagged these need to have values. This class holds these values, and tells the program boolean 
 * values of a cell. The cell at any time in the game is a combination of Mine, Flag, Blank. 
 * @author matthew_duerr
 *
 */
public class Cell {
	/**
	 * Each boolean tells the program the state of the cell based upon if is a mine, if is flagged, and if it blank or revealed.
	 */
	private boolean Mine, Flag, Blank;
	/**
	 * The actual number of the cell, 1-8 depending on how many mines are around it. 
	 */
	private int number;
	/**
	 * The actual cell in the beginning has no mine,flag, or number and is blank
	 */
	public Cell()
	{
		Mine = false;
		Flag = false;
		Blank = true;
		number = 0;
	}
	/**
	 * The program must at all times know whether or not a cell is a mine, this method simply states if a mine is set that it is a mine.
	 */
	public void setMine()
	{
		Mine = true;
	}
	/**
	 * If a flag is placed by the user then this method tells the program that the cell selected is now a flag. 
	 */
	public void flag()
	{
		Flag = true;
	}
	/**
	 * When a flag is undone by the user or the cell was never a flag at all then the cell with this method will return to it never having a flag.
	 */
	public void unflag()
	{
		Flag = false;
	}
	/**
	 * All cells start out blank and then the program after launching or reseting will reveal certain cells to the user in order for the user to play, this method replaces the cells original 
	 * blank starting and shows the value underneath.
	 */
	public void reveal()
	{
		Blank = false;
	}
	/**
	 * Most cells have numbers, after the value for the cell is created it is placed here and then painted in the Board class.
	 */
	public void setNumber(int i)
	{
		number = i;
	}
	/**
	 * Even though we placed the mines this will tell the program whether or not that cell is a mine. Although the user sees a blank cell the program already understands if the cell is a mine.
	 */
	public boolean Mine()
	{
		return Mine;
	}
	/**
	 * Returns to the program whether or not the user placed a flag, this also tells the program whether or not the flag is gone and changes boolean to false if so
	 */
	public boolean Flag()
	{
		return Flag;
	}
	/**
	 * Tells program if cell is blank, meaning if the user has yet to click the cell. A cell can still be blank if clicked if the user right clicks and places a flag.
	 */
	public boolean Blank()
	{
		return Blank;
	}
	/**
	 * Returns the number of the cell, numbers 1-8 depending on how many surrounding mines.
	 */
	public int getNumber()
	{
		return number;
	}
}
