package pacman;

public class PowerPellet extends FoodItem{

	public PowerPellet(Square square)
	{
		super(square);
	}
	

	@Override
	/**
	 * Returns the size of the FoodItem relative to the size of the dot
	 * @basic
	 * @post result == 2
	 */
	public int getSize() {
		return 2;
	}
	
	@Override
	public void eatenByPacMan(Maze maze) {
		maze.pacManAtePowerPellet();
	}
}
