package pacman;


public class Dot extends FoodItem{
	
	public Dot(Square square)
	{
		super(square);
	}

	@Override
	public int getSize() {
		return 1;
	}

}
