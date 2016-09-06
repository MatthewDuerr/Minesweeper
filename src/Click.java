import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Handles all user input, to be precise any time the user clicks on a cell, whether it is left or right click, the coordinates of the click 
 * are given to the class, and with that knowledge the appropriate cell is selected.
 * @author matthew_duerr
 *
 */
public class Click implements MouseListener, ActionListener{

	/**
	 * The actual cell that was clicked
	 */
	private MineSweeper mine;
	/**
	 * r is the representation of the columns and rows, it all helps determine which cell you click, if the value is change to a different value 
	 * then the wrong cell will be picked
	 */
	private int r = 26;
	/**
	 * 
	 * @param mainframe
	 */
	public Click(MineSweeper mainframe)
	{
		mine = mainframe;
	}

	public void actionPerformed(ActionEvent e) {

		mine.getGame().reset();
		mine.getGame().refresh();

	}
	/**
	 * When user left or right clicks the x and y coordinates of the click are given to the program in order to determine what cell was clicked and how
	 * it was clicked, depending on what type of click the program reveals or flags.
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1)
		{
			int x = e.getX() / r;
			int y = e.getY() / r;

			mine.getGame().select(x, y);
		}

		if (e.getButton() == 3)
		{
			int x = e.getX() / r;
			int y = e.getY() / r;

			mine.getGame().mark(x, y);
		}

		mine.getGame().refresh();

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}


	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

}
