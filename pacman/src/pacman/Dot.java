package pacman;

/**
 * Each instance of this class represents a dot, located at a fixed position in a Pac-Man maze.
 * A dot serves as the food for Pac-Man.
 * 
 * @invar | getSquare() != null
 * 
 * @immutable
 */
public class Dot extends FoodItem{
	
	public Dot(Square square)
	{
		super(square);
	}
	
	

	@Override
	/**
	 * Returns the size of the FoodItem relative to the size of the dot
	 * @post result == 1
	 */
	public int getSize() {
		return 1;
	}

}
