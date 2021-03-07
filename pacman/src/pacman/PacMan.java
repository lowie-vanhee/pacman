package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * 
 * public class invariants
 * 
 * @invar The number of lives is always positive 
 * 		  | getNbLives() >= 0
 * @invar The square is never {@code: null} 
 * 		  | getSquare() != null
 * 
 */
public class PacMan {
	
	/**
	 * private class invariants
	 *
	 * @invar | nbLives >= 0
	 * @invar | square != null
	 * 
	 */
	
	private int nbLives;
	private Square square;
	
	/**
	 * Returns the square where this pacman is located
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	/**
	 * Returns the amount of lives pacman has
	 * @basic
	 */
	public int getNbLives() { return nbLives; }
	
	/**
	 * Initializes this PacMan object with the given amount of lives located at the given square
	 * 
	 * @throws IllegalArgumentException if square is {@code null} | square == null
	 * @throws IllegalArgumentException if nbLives is not greater than zero | nbLives <= 0
	 * 
	 * @post | getNbLives() == nbLives
	 * @post | getSquare() == square
	 */
	public PacMan(int nbLives, Square square) {
		if(square == null)
			throw new IllegalArgumentException("The square argument cannot be null");
		if(nbLives <= 0)
			throw new IllegalArgumentException("The nbLives argument must be greater than zero");
			
		this.nbLives = nbLives;
		this.square  = square;
	}
	
	/**
	 * Moves pacman to the given square
	 * 
	 * @mutates | this
	 * 
	 * @throws IllegalArgumentException if square is {@code null} | square == null
	 * 
	 * @post | getNbLives() == old(getNbLives())
	 * @post | getSquare() == square
	 */
	public void setSquare(Square square) { 
		if(square == null)
			throw new IllegalArgumentException("The square argument must not be null");
		
		this.square = square;
	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 * 
	 * @throws IllegalStateException if called when PacMan has no lives left 
	 * 		   | getNbLives() <= 0
	 * 
	 * @mutates | this
	 * 
	 * @post | getSquare() == old(getSquare())
	 * @post | getNbLives() == old(getNbLives())-1
	 */
	public void die() {
		if(nbLives <= 0)
			throw new IllegalStateException("die() cannot be called when PacMan has no lives left");
		
		this.nbLives--; 
	}

}
