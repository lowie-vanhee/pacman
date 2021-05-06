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
	 * @invar | departure != null
	 */
	
	private DeparturePortal departure;
	private ArrivalPortal arrival;
	
	/**
	 * @basic
	 */
	public DeparturePortal getDeparturePortal()
	{
		return departure;
	}
	/**
	 * @basic
	 */
	public ArrivalPortal getArrivalPortal()
	{
		return arrival;
	}
	
	/**
	 * @mutates | this
	 * @throws IllegalArgumentException | departure == null
	 * 
	 * @post | getArrivalPortal() == old(getArrivalPortal())
	 * @post | getDeparturePortal() == departure
	 */
	public void setDeparturePortal(DeparturePortal departure)
	{
		if(departure == null) throw new IllegalArgumentException("Departure cannot be null");
		this.departure = departure;
	}
	
	/**
	 * @mutates | this
	 * @throws IllegalArgumentException | arrival == null
	 * 
	 * @post | getArrivalPortal() == arrival
	 * @post | getDeparturePortal() == old(getDeparturePortal())
	 */
	public void setArrivalPortal(ArrivalPortal arrival)
	{
		if(arrival == null) throw new IllegalArgumentException("Arrival cannot be null");
		this.arrival = arrival;
	}		
	
	/**
	 * @throws IllegalArgumentException | departure == null
	 * @throws IllegalArgumentException | arrival == null
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
