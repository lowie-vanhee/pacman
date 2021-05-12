package pacman.wormholes;

import logicalcollections.LogicalSet;

/**
 * @invar | getDeparturePortal() != null
 * @invar | getArrivalPortal()  != null
 *
 */
public class Wormhole {
	
	/**
	 * @invar | arrival != null
	 * @invar arrival portal's list of wormholes must contain this | arrival.getWormholesInternal().stream().anyMatch(w -> w == this)
	 * @invar | departure != null
	 * @invar | departure.getWormholesInternal().stream().anyMatch(w -> w == this)
	 */
	/**
	 * @peerObject
	 */
	private DeparturePortal departure;
	/**
	 * @peerObject
	 */
	private ArrivalPortal arrival;
	
	/**
	 * @basic
	 * @peerObject
	 */
	public DeparturePortal getDeparturePortal()
	{
		return departure;
	}
	/**
	 * @basic
	 * @peerObject
	 */
	public ArrivalPortal getArrivalPortal()
	{
		return arrival;
	}
	
	/**
	 * @mutates | this, departure
	 * @throws IllegalArgumentException | departure == null
	 * 
	 * @post | getArrivalPortal() == old(getArrivalPortal())
	 * @post | getDeparturePortal() == departure
	 * @post If the new departure portal is the same as the current departure portal, then the portal's wormholes set is unaffected
	 * 		else, the new departure portal's wormholes set is equal to the old set plus this wormhole
	 * 		and the old departure portal's wormholes is equal to old minus this |
	 * 
	 * 		(old(getDeparturePortal()) == departure)?
	 *  		old(departure.getWormholes()).equals(departure.getWormholes()) : 
	 *  			departure.getWormholes().equals(LogicalSet.plus(old(departure.getWormholes()), this)) &&
	 *   			old(getDeparturePortal()).getWormholes().equals(LogicalSet.minus(old(getDeparturePortal().getWormholes()), this))
	 * 
	 * @post The arrival portal's wormholes remain exactly the same | old(getArrivalPortal().getWormholes()).equals(getArrivalPortal().getWormholes())
	 */
	public void setDeparturePortal(DeparturePortal departure)
	{
		if(departure == null) throw new IllegalArgumentException("Departure cannot be null");
		
		this.departure.getWormholesInternal().remove(this);
		this.departure = departure;
		departure.getWormholesInternal().add(this);
	}
	
	/**
	 * @mutates | this, arrival
	 * @throws IllegalArgumentException | arrival == null
	 * 
	 * @post | getArrivalPortal() == arrival
	 * @post | getDeparturePortal() == old(getDeparturePortal())
	 */
	public void setArrivalPortal(ArrivalPortal arrival)
	{
		if(arrival == null) throw new IllegalArgumentException("Arrival cannot be null");
		//--Hier nog testen of de nieuwe departure portal niet de huidige is ? ma vo maximale punte ni teste => ingewikkelde docs--
		this.arrival.getWormholesInternal().remove(this);
		this.arrival = arrival;
		arrival.getWormholesInternal().add(this);
	}		
	
	/**
	 * @throws IllegalArgumentException | departure == null
	 * @throws IllegalArgumentException | arrival == null
	 * 
	 * @mutates | departure, arrival
	 * 
	 * @post | getDeparturePortal() == departure
	 * @post | getArrivalPortal() == arrival
	 * @post | departure.getWormholes().equals(LogicalSet.plus(old(departure.getWormholes()), this))
	 * @post | arrival.getWormholes().equals(LogicalSet.plus(old(arrival.getWormholes()), this))
	 * 
	 */
	public Wormhole(DeparturePortal departure, ArrivalPortal arrival)
	{
		if(departure == null) throw new IllegalArgumentException("Departure cannot be null");
		if(arrival == null) throw new IllegalArgumentException("Arrival cannot be null");
		
		departure.getWormholesInternal().add(this);
		arrival.getWormholesInternal().add(this);
		
		this.departure = departure;
		this.arrival = arrival;
	}

}
