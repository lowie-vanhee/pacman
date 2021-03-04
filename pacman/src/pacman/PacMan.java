package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * 
 * @invar The number of lives is always positive.
 * 		| getNbLives() >= 0
 * @invar The square is never {@code: null}.
 * 		| getSquare() != null
 */
public class PacMan {
	
	/**
	 * @invar | nbLives >= 0
	 * @invar | square != null
	 */
	private int nbLives;
	/**
	 * @representationObject
	 */
	private Square square;
	
	/**
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	/**
	 * @basic
	 */
	public int getNbLives() { return nbLives; }

	/**
	 * @throws IllegalArgumentException when nbLives is negative
	 * 		| nbLives < 0
	 * @throws IllegalArgumentException when square is {@code: null}
	 * 		| square == null
	 * 
	 * @inspects square
	 * 
	 * @post | getNbLives() == nbLives
	 * @post | getSquare() == square
	 */
	public PacMan(int nbLives, Square square) {
		if(nbLives < 0) 
			throw new IllegalArgumentException("The given number of lives must not be negative");
		if(square == null) 
			throw new IllegalArgumentException("The given square must not be null");
		
		this.nbLives = nbLives;
		this.square  = square;
	}
	
	/**
	 * @throws IllegalArgumentException if square is {@code: null}
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
	 * Decreases this Pac-Man character's number of lives by one.
	 * @post | getNbLives() == old(getNbLives()) - 1
	 */
	public void die() { this.nbLives--; }

}
