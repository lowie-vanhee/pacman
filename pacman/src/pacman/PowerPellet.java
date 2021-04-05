package pacman;

public class PowerPellet extends FoodItem{

	public PowerPellet(Square square)
	{
		super(square);
	}
	
	@Override
	public boolean isPowerPellet() {
		return true;
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
}
