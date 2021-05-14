package pacman.wormholes;

import logicalcollections.LogicalSet;

/**
 * @invar | getDeparturePortal() != null && getDeparturePortal().getWormholes().contains(this)
 * @invar | getArrivalPortal()  != null && getArrivalPortal().getWormholes().contains(this)
 *
 */
public class Wormhole {
	
	/**
	 * @invar | arrival != null
	 * @invar | departure != null
	 */
	
	private DeparturePortal departure;
	
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
	 * @peerObject
	 * @invar  | getDeparturePortalInternal().getWormholesInternal().contains(this)
	 * @post | result != null
	 */
	
	DeparturePortal getDeparturePortalInternal()
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
	 * @peerObject
	 * @invar | getArrivalPortalInternal().getWormholesInternal().contains(this)
	 * @post | result != null
	 */
	ArrivalPortal getArrivalPortalInternal()
	{
		return arrival;
	}
	
	/**
	 * @mutates_properties | this.getDeparturePortal(), this.getDeparturePortal().getWormholes(), departure.getWormholes()
	 * @throws IllegalArgumentException | departure == null
	 * 
	 * @post | getArrivalPortal() == old(getArrivalPortal())
	 * @post | getDeparturePortal() == departure
	 * @post If the new departure portal is the same as the current departure portal, then the portal's wormholes set is unaffected
	 * 		else, the new departure portal's wormholes set is equal to the old set plus this wormhole
	 * 		and the old departure portal's wormholes is equal to the old set minus this
	 * 		 |(old(getDeparturePortal()) == departure)?
	 *  	 |	old(departure.getWormholes()).equals(departure.getWormholes()) : 
	 *  	 |		departure.getWormholes().equals(LogicalSet.plus(old(departure.getWormholes()), this)) &&
	 *   	 |	old(getDeparturePortal()).getWormholes().equals(LogicalSet.minus(old(getDeparturePortal().getWormholes()), this))
	 * 
	 * @post The arrival portal's wormholes remain exactly the same | old(getArrivalPortal().getWormholes()).equals(getArrivalPortal().getWormholes())
	 */
	public void setDeparturePortal(DeparturePortal departure)
	{
		if(departure == null) throw new IllegalArgumentException("Departure cannot be null");
		
		this.departure.removeWormhole(this);
		this.departure = departure;
		departure.addWormhole(this);;
	}
	
	/**
	 * @mutates_properties |  this.getArrivalPortal(), this.getArrivalPortal().getWormholes(), arrival.getWormholes()
	 * @throws IllegalArgumentException | arrival == null
	 * 
	 * @post | getArrivalPortal() == arrival
	 * @post | getDeparturePortal() == old(getDeparturePortal())
	 * 
	 * @post If the new departure portal is the same as the current departure portal, then the portal's wormholes set is unaffected
	 * 		else, the new departure portal's wormholes set is equal to the old set plus this wormhole
	 * 		and the old departure portal's wormholes is equal to the old set minus this
	 * 		 |(old(getArrivalPortal()) == arrival)?
	 *  	 |	old(arrival.getWormholes()).equals(arrival.getWormholes()) : 
	 *  	 |		arrival.getWormholes().equals(LogicalSet.plus(old(arrival.getWormholes()), this)) &&
	 *   	 |			old(getArrivalPortal()).getWormholes().equals(LogicalSet.minus(old(getArrivalPortal().getWormholes()), this))
	 * 
	 * @post The departure portal's wormholes remain exactly the same | old(getDeparturePortal().getWormholes()).equals(getDeparturePortal().getWormholes())
	 */
	public void setArrivalPortal(ArrivalPortal arrival)
	{
		if(arrival == null) throw new IllegalArgumentException("Arrival cannot be null");
		
		this.arrival.removeWormhole(this);
		this.arrival = arrival;
		arrival.addWormhole(this);
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
		
		departure.addWormhole(this);;
		arrival.addWormhole(this);
		
		this.departure = departure;
		this.arrival = arrival;
	}

}
