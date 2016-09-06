import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Sets up the JFrame and menu bar, this isn't the game that a user sees painted but instead the program window surrounding the game, with 
 * that being said each item a user can select must be coded here. This includes the x button around as each category in menu bar and 
 * subcategories. 
 * @author matthew_duerr
 *
 */
public class MineSweeper extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private int width, height;
	private Game game;	
	private Board currentBoard;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmDifficulty;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnFile_1;
	private JMenuItem mntmReset;
	private JMenuItem mntmExit;
	private JMenuItem mntmRules;
	/**
	 * Starts program and launches frame
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				int x = 26;		//Width of the board
				int y = 26;		//Height of the board

				try {
					MineSweeper frame = new MineSweeper(x, y);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 
	 * @param x Amount of columns of cells the game will contain
	 * @param y Amount of rows the game will contain
	 */
	public MineSweeper(int x, int y) {

		width = x;
		height = y;

		game = new Easy(width, height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile_1 = new JMenu("File");
		menuBar.add(mnFile_1);

		mntmReset = new JMenuItem("Reset");
		mntmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.reset();
				game.refresh();
			}
		});
		mnFile_1.add(mntmReset);

		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		mnFile_1.add(mntmExit);

		mnFile = new JMenu("Difficulty");
		menuBar.add(mnFile);

		mntmDifficulty = new JMenuItem("Easy");
		mntmDifficulty.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				game = new Easy(width, height);
				reset();
			}
		});
		mnFile.add(mntmDifficulty);

		mntmNewMenuItem = new JMenuItem("Medium");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game = new Medium(width, height);
				reset();
			}
		});
		mnFile.add(mntmNewMenuItem);

		mntmNewMenuItem_1 = new JMenuItem("Hard");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game = new Hard(width, height);
				reset();
			}
		});
		mnFile.add(mntmNewMenuItem_1);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About...");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MineSweeper.this, "Welcome to MineSweeper! This program was created as the final project"
						+ " of Matthew J Duerr at the end of the 2013 Fall Semester for CSC 211 at the Universiy of Rhode Island");
			}
		});

		mntmRules = new JMenuItem("Rules");
		mntmRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MineSweeper.this, "1. Left click to uncover tile \n2. Right click to tag possible mines "
						+ "\n3. The number in the box displays how many mines are in the 8 boxes surrounding \n4. Flag all mines to win");
			}
		});
		mnHelp.add(mntmRules);
		mnHelp.add(mntmAbout);
		contentPane = new JPanel(); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		reset();

		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);
	}
	/**
	 * Any time the difficulty is changed, the user wins or loses a game, or wants to start over, reset is called in order to reset the board
	 */
	private void reset(){
		if(currentBoard != null){
			getContentPane().remove(currentBoard);
		}

		game.reset();

		currentBoard = game.getBoard();

		currentBoard.addMouseListener(new Click(this));

		getContentPane().add(currentBoard, BorderLayout.CENTER);

		this.invalidate();
		this.validate();

		game.refresh();
	}

	/**
	 * return board width 
	 * @return
	 */
	public int getx()
	{
		return width;
	}
	/**
	 * return board height
	 * @return
	 */
	public int gety()
	{
		return height;
	}
	/**
	 * return game type
	 * @return
	 */
	public Game getGame(){
		return game;
	}
}
