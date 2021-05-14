package pacman.wormholes;

import java.util.Set;
import logicalcollections.LogicalSet;

import pacman.Square;

/**
 * @invar | getSquare() != null
 * @invar | getWormholes() != null && getWormholes().stream().allMatch(w -> w != null)
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
	 * @invar | wormholes.stream().allMatch(w -> w != null)
	 * @peerObjects
	 */
	 private java.util.HashSet<Wormhole> wormholes;
	
	/**
	 * @basic
	 * @peerObjects
	 * @creates | result
	 * @invar | getWormholesInternal().stream().allMatch(w -> w.getDeparturePortalInternal() == this)
	 * @post | result != null && result.stream().allMatch(w -> w != null)
	 */
	java.util.Set<Wormhole> getWormholesInternal()
	{
		return Set.copyOf(wormholes);
	}
	
	/**
	 * @throws IllegalArgumentException | wormhole == null
	 * @mutates | this
	 * @post | getWormholesInternal().equals(LogicalSet.plus(old(getWormholesInternal()), wormhole))
	 */
	void addWormhole(Wormhole wormhole)
	{
		if(wormhole == null)
			throw new IllegalArgumentException("Wormhole cannot be null");
		wormholes.add(wormhole);
	}
	
	/**
	 * @throws IllegalArgumentException | wormhole == null
	 * @mutates | this
	 * @post | getWormholesInternal().equals(LogicalSet.minus(old(getWormholesInternal()), wormhole))
	 */
	void removeWormhole(Wormhole wormhole)
	{
		if(wormhole == null)
			throw new IllegalArgumentException("Wormhole cannot be null");
		wormholes.remove(wormhole);
	}
	
	/**
	 * @basic
	 * @post | result != null
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
