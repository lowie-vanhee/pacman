package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @immutable
 * 
 * @invar The square is never {@code:null}
 * 		  | getSquare() != null
 */
public class Dot {
	
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
	 * Initializes this Dot so it is located at the given square
	 * 
	 * @throws IllegalArgumentException if square is {@code null} | square == null
	 * 
	 * @post | getSquare() == square
	 */
	public Dot(Square square) {
		if(square == null)
			throw new IllegalArgumentException("The square argument cannot be null");
		
		this.square = square;
	}

}
