package pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

public class Maze {
	
	private ArrayList<Wormhole> wormholes;
	private ArrayList<DeparturePortal> departurePortals;
	private ArrayList<ArrivalPortal> arrivalPortals;
	
	public void addWormhole(Wormhole wormhole)
	{
		if(!(departurePortals.stream().anyMatch(e -> e == wormhole.getDeparturePortal()) && arrivalPortals.stream().anyMatch(e -> e == wormhole.getArrivalPortal())))
			throw new IllegalArgumentException("One of the wormhole's portals does not exist in this maze");
		wormholes.add(wormhole);
	}
	
	public Wormhole[] getWormholes()
	{
		//Can we fix this shite??
		Wormhole[] result = new Wormhole[wormholes.size()];
		result =  wormholes.toArray(result);
		return result;
	}
	
	public DeparturePortal[] getDeparturePortals()
	{
		DeparturePortal[] result = new DeparturePortal[departurePortals.size()];
		result = departurePortals.toArray(result);
		return result;
	}
	
	public ArrivalPortal[] getArrivalPortals()
	{
		ArrivalPortal[] result = new ArrivalPortal[arrivalPortals.size()];
		result =  arrivalPortals.toArray(result);
		return result;
	}
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foodItems;
	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foodItems.clone(); }
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems, DeparturePortal[] departurePortals, ArrivalPortal[] arrivalPortals) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
		
		this.departurePortals = new ArrayList<DeparturePortal>(Arrays.asList(departurePortals));
		this.arrivalPortals = new ArrayList<ArrivalPortal>(Arrays.asList(arrivalPortals));
		this.wormholes = new ArrayList<Wormhole>();
		
	}
	
	public boolean isCompleted() {
		return foodItems.length == 0;
	}
	
	private void checkPacManDamage(Square square) {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(square))
				ghost.hitBy(pacMan);
	}
	
	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage(pacMan.getSquare());
	}
	
	public void pacManAtePowerPellet() {
		for (Ghost ghost : ghosts)
			ghost.pacManAtePowerPellet();
	}
	
	private void removeFoodItemsAtIndex(int index) {
		FoodItem[] newFoodItems = new FoodItem[foodItems.length - 1];
		System.arraycopy(foodItems, 0, newFoodItems, 0, index);
		System.arraycopy(foodItems, index + 1, newFoodItems, index, newFoodItems.length - index);
		foodItems = newFoodItems;
	}
	
	private void checkFoodItemCollision(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				foodItems[i].eatenByPacMan(this);
				removeFoodItemsAtIndex(i);
				return;
			}
		}
	}
	
	private Square checkDeparturePortal(Square square)
	{
		Square newSquare = square;
		
		for(DeparturePortal p : departurePortals)
		{
			if(p.getSquare().equals(square))
			{
				if(p.getWormholes().size() != 0) 
				{
					Wormhole[] potentialWormholesToEnter = new Wormhole[p.getWormholes().size()];
					
					potentialWormholesToEnter = p.getWormholes().toArray(potentialWormholesToEnter);
					
					Wormhole chosen = potentialWormholesToEnter[random.nextInt(potentialWormholesToEnter.length)];
					newSquare = chosen.getArrivalPortal().getSquare();
				}
			}
		}

		return newSquare;
		
	}
	
	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		Square oldSquare = newSquare;
		if (newSquare.isPassable()) {
			checkPacManDamage(newSquare); 
			newSquare = checkDeparturePortal(newSquare); // newSquare remains the same if there was no departure portal, else 
			pacMan.setSquare(newSquare);
			checkFoodItemCollision(newSquare);
			if(!oldSquare.equals(newSquare))
			{
				checkPacManDamage(newSquare);
			}
			
		}
	}
	
}
