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
	public int getSize() {
		return 2;
	}
}
