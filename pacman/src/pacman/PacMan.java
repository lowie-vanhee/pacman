package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 */
public class PacMan {
	
	private int nbLives;
	private Square square;
	
	public Square getSquare() { return square; }
	
	public int getNbLives() { return nbLives; }

	public PacMan(int nbLives, Square square) {
		this.nbLives = nbLives;
		this.square  = square;
	}
	
	public void setSquare(Square square) { this.square = square; }
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 */
	public void die() { this.nbLives--; }

}
