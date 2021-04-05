package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 * 
 * @invar The square is never {@code null} 
 * 		  | getSquare() != null
 * @invar The direction is never {@code null}
 * 		  | getDirection() != null
 */
public class Ghost {
	
	/**
	 * @invar | square != null
	 * @invar | direction != null
	 */
	
	private GhostState state = new RegularGhostState();

	private Square square;
	private Direction direction;
	
	private Square originalSquare;
	
	public boolean isVulnerable() {
		return state.isVulnerable();
	}
	
	public void pacManAtePowerPellet() {
		direction = direction.getOpposite();
		this.state = new VulnerableGhostState();
	}
	
	/**
	 * Returns the square where this ghost is located at
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	public Square getOriginalSquare() {return originalSquare;}
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 * @basic
	 */
	public Direction getDirection() { return direction; }
	
	/**
	 * Initializes this ghost at the given square, preferring to go in the given direction
	 * 
	 * @throws IllegalArgumentException if square is {@code null} | square == null
	 * @throws IllegalArgumentException if direction is {@code null} | direction == null
	 * 
	 * @post | getSquare() == square
	 * @post | getDirection() == direction
	 */
	public Ghost(Square square, Direction direction) {
		if(square == null)
			throw new IllegalArgumentException("The given square cannot be null");
		if(direction == null)
			throw new IllegalArgumentException("The given direction cannot be null");
		
		this.square = square; 
		this.originalSquare = square;
		this.direction = direction;
	}
	
	/**
	 * @throws IllegalArgumentExcption if square is {@code null} | square == null
	 * 
	 * @mutates | this
	 * 
	 * @post | getDirection() == old(getDirection())
	 * @post | getSquare() == square
	 */
	public void setSquare(Square square) {
		if(square == null)
			throw new IllegalArgumentException("The given square cannot be null");
		this.square = square; 
	}
	
	/**
	 * @throws IllegalArgumentExcption if direction is {@code null} | direction == null
	 * 
	 * @mutates | this
	 * 
	 * @post | getSquare() == old(getSquare())
	 * @post | getDirection() == direction
	 */
	public void setDirection(Direction direction) { 
		if(direction == null)
			throw new IllegalArgumentException("The given direction cannot be null");
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
	public void reallyMove(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
	
	public void move(Random random) { 
		state = state.move(this, random);
	}
	
	public void hitBy(PacMan pacMan) {
		state = state.hitBy(this, pacMan);
	}
}
