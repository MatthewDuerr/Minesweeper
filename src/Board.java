import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * Our graphics class, the game itself is painted in this class, including each sell, mine, flag and general game.
 * @author matthew_duerr
 *
 */
public class Board extends JPanel
{
	private static final long serialVersionUID = 1L;
	/**
	 * Actual mine
	 */
	private Game mine;
	private Cell[][] cells;
	/**
	 * The variable is critical in how each item is drawn, including numbers, with this number alone you can change the size of every item proportionally, meaning instead of having
	 * multiple values to change you can simply change one.
	 */
	private int r = 26;
	/**
	 * Figures out which cells are mines
	 * @param game
	 */
	public Board(Game game)
	{
		mine = game;
		cells = mine.getCells();

		setPreferredSize(new Dimension(mine.getWidth() * r, mine.getHeight() * r));
	}

	public void paintComponent(Graphics g)
	{
		cells = mine.getCells();

		for (int i = 0; i < mine.getWidth(); i++)
		{
			for (int j = 0; j < mine.getHeight(); j++)
			{
				Cell current = cells[i][j];

				if (current.Flag()) // if cell was flagged then paint based upon circumstances 
				{
					if (current.Mine() && mine.isFinished()) //if game is over and a mine then paint green
					{
						g.setColor(Color.GREEN);
						g.fillRect(i * r, j * r, i * r + r, j * r + r);
						g.setColor(Color.BLACK);

						g.drawLine(i * r, j * r, i * r + r, j * r + r);
						g.drawLine(i * r, j * r + r, i * r + r, j * r);
					}
					else if (mine.isFinished()) //if game is finished and not a mine paint magenta
					{
						g.setColor(Color.MAGENTA);
						g.fillRect(i * r, j * r, i * r + r, j * r + r);
						g.setColor(Color.BLACK);
					}
					else
					{
						g.setColor(Color.YELLOW); //if not paint yellow when flagged
						g.fillRect(i * r, j * r, i * r + r, j * r + r);
						g.setColor(Color.BLACK);
					}
				}
				else if (current.Blank()) //color of blanks
				{
					g.setColor(Color.CYAN);
					g.fillRect(i * r, j * r, i * r + r, j * r + r);
					g.setColor(Color.BLACK);
				}
				else if (current.Mine()) //if a mine 
				{
					g.setColor(Color.RED);
					g.fillRect(i * r, j * r, i * r + r, j * r + r);
					g.setColor(Color.WHITE);
					g.drawLine(i * r, j * r, i * r + r, j * r + r);
					g.drawLine(i * r, j * r + r, i * r + r, j * r);
				}
				else
				{
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(i * r, j * r, i * r + r, j * r + r);
					g.setColor(Color.BLACK);
				}
				if (!current.Blank()) //if blank and not a mine then print numbers
				{
					if(current.getNumber() > 0 && current.getNumber() <= 8){
						g.setColor(Color.BLACK);setForeground(Color.BLACK);
						g.setFont(new Font("Cambria", Font.BOLD, 20));
						g.drawString(String.valueOf(current.getNumber()), i * r +r/3, j * r + 3*r/4);
					}
				}
				g.setColor(Color.BLACK);
				g.drawRect(i * r, j * r, i * r + r, j * r + r);
			}
		}
	}
}
