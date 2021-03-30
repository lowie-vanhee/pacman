package pacman;

/**
 * Each instance of this class represents a FoodItem (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @immutable
 * 
 * @invar The square is never {@code:null}
 * 		  | getSquare() != null
 */
public abstract class FoodItem {
	
	/**
	 * @invar | square != null
	 */
	private Square square;
	
	/**
	 * Returns the square where this dot is located
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	/**
	 * Initializes this FoodItem so it is located at the given square
	 * 
	 * @throws IllegalArgumentException if square is {@code null} | square == null
	 * 
	 * @post | getSquare() == square
	 */
	public FoodItem(Square square) {
		if(square == null)
			throw new IllegalArgumentException("The square argument cannot be null");
		
		this.square = square;
	}
	
	
	/**
	 * DOCUMENTATIE SCHRIOCJJNEN 
	 */
	public abstract int getSize();
	
	public boolean isPowerPellet() {
		return false;
	}

}
