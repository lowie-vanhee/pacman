package pacman.wormholes;

import java.util.Set;

import pacman.Square;

/**
 * @invar | getSquare() != null
 * @invar | getWormholes() != null
 */
public class ArrivalPortal {
	
	/**
	 * @invar | square != null
	 */
	private Square square;
	
	/**
	 * @representationObject
	 * @invar | wormholes != null
	 */
	private java.util.Set<Wormhole> wormholes;
	
	/**
	 * @basic 
	 */
	java.util.Set<Wormhole> getWormholesInternal()
	{
		return wormholes;
	}
	/**
	 * @basic
	 */
	public Square getSquare()
	{
		return square;
	}
	
	/**
	 * @basic
	 */
	public java.util.Set<Wormhole> getWormholes()
	{
		return Set.copyOf(wormholes);
	}
	
	/**
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getSquare() == square
	 *
	 */
	public ArrivalPortal(Square square)
	{
		if(square == null) throw new IllegalArgumentException("square cannot be null");
		this.square = square;
	}

}
