package pacman.wormholes;

import java.util.Set;

import pacman.Square;

/**
 * @invar | getSquare() != null
 * @invar | getWormholes() != null
 * 
 */
public class DeparturePortal {
	
	/**
	 * @invar | square != null
	 */
	private Square square;
	
	/**
	 * @representationObject
	 * @invar | wormholes != null
	 * @invar | wormholes.stream().allMatch(w -> w != null && w.getDeparturePortal() == this)
	 * @peerObjects
	 */
	private java.util.HashSet<Wormhole> wormholes;
	
	/**
	 * @basic
	 * @peerObjects
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
	 * @representationObjects
	 * @peerObjects
	 * @creates | result
	 * @invar | getWormholes() != null
	 */
	public java.util.Set<Wormhole> getWormholes()
	{
		return Set.copyOf(wormholes);
	}

	/**
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getSquare() == square
	 * @post | getWormholes().isEmpty()
	 *
	 */
	public DeparturePortal(Square square)
	{
		if(square == null) throw new IllegalArgumentException("square cannot be null");
		this.square = square;
		wormholes = new java.util.HashSet<Wormhole>();
	}
}
