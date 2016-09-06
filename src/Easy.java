
/**
 * 
 * When clicked from difficulty menu it will choose to the Easy class, which has a different difficulty value and will send the "Easy" 
 * difficulty value to the abstract game class. 
 * @author matthew_duerr
 *
 */
public class Easy extends Game {

	/**
	 * Contains our super() in order to add our difficulty value for the constructor
	 * @param w Width of board, board width is not measured in pixels but in cells.
	 * @param h Height of board, board height is not measured in pixels but in cells.
	 */
	public Easy(int w, int h) {
		super(w, h);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Each level has a difficulty, the reason behind the difficulty classes (Easy, Medium, Hard) is to place retrieve the difficulty within 
	 * the class, this method is here to give that difficulty. If difficulty is changed from 20 to a different number, then when ever the 
	 * user is playing an Easy level there would be instead of a 20 percent change of mines there would be a newer percentage. This number 
	 * is directly sent to the game class.
	 */
	public int getDifficulty() {
		return 20;
	}

}
