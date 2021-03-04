package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 */
public class Ghost {
	
	/**
	 * @invar | square != null
	 * @representationObect
	 */
	private Square square;
	/**
	 * @invar | direction != null
	 * @representationObject
	 */
	private Direction direction;
	
	/**
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 * @basic
	 */
	public Direction getDirection() { return direction; }
	
	/**
	 * @throws IllegalArgumentException if square is {@code: null}.
	 * 		| square == null
	 * @throws IllegalArgumentException if direction is {@code: null}.
	 * 		| direction == null
	 * 
	 * @inspects | square
	 * @inspects | direction
	 * 
	 * @post | getSquare() == square
	 * @post | getDirection() == direction
	 */
	public Ghost(Square square, Direction direction) 
	{
		if(square == null)
			throw new IllegalArgumentException("The given square must not be null");
		if(direction == null)
			throw new IllegalArgumentException("The given direction must not be null");
		
		this.square = square; 
		this.direction = direction;
	}
	
	/**
	 * @throws IllegalArgumentException if square is {@code: null}.
	 * 		| square == null
	 * @post | getSquare() == square
	 */
	public void setSquare(Square square) 
	{
		if(square == null)
			throw new IllegalArgumentException("The given square must not be null");
		this.square = square; 
	}
	
	/**
	 * @throws IllegalArgumentException if direction is {@code: null}.
	 * 		| direction == null
	 * @post | getDirection() == direction
	 */
	public void setDirection(Direction direction) 
	{ 
		if(direction == null)
			throw new IllegalArgumentException("The given direction must not be null");
		this.direction = direction; 
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal document required
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal document required
	public void move(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
}
