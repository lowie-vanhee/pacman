package pacman;


public class Dot extends FoodItem{
	
	public Dot(Square square)
	{
		super(square);
	}
	
	

	@Override
	/**
	 * Returns the size of the FoodItem relative to the size of the dot
	 * @basic
	 * @post result == 1
	 */
	public int getSize() {
		return 1;
	}



	@Override
	public void eatenByPacMan(Maze maze) {/*pacman ate no pp today :( #gay rights*/}

}
