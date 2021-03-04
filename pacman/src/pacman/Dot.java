package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @invar The square is never {@code:null}.
 * 		| getSquare() != null
 */
public class Dot {
	
	/**
	 * @invar | square != null
	 * @representationObject
	 */
	private Square square;
	
	/**
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	/**
	 * @throws IllegalArgumentException when square is {@code: null}.
	 * 		| square == null
	 * 
	 * @inspects | square
	 * 
	 * @post | getSquare() == square
	 */
	public Dot(Square square) 
	{ 
		if(square == null)
			throw new IllegalArgumentException("The given square must not be null");
		this.square = square; 
	}

}
