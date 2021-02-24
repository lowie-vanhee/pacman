package pacman;

import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 */
public class Square {
	
	private MazeMap mazeMap;
	
	private int rowIndex;
	private int columnIndex;
	
	public MazeMap getMazeMap() { return mazeMap; }
	
	public int getRowIndex() { return rowIndex; }
	
	public int getColumnIndex() { return columnIndex; }
	
	public boolean isPassable() { return mazeMap.isPassable(rowIndex, columnIndex); }
	
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) 
	{
		Square square = new Square();
		
		square.rowIndex = rowIndex;
		square.columnIndex = columnIndex;
		square.mazeMap = mazeMap;
		
		return square;
	}
	
	/**
	 * Returns this square's neighbor in the given direction.
	 * If this square has no neigbor in the given direction, return the square that is furthest away in the opposite direction.
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) {
		// Implementation hint: use method java.lang.Math.floorMod.
		switch(direction)
		{
		case DOWN: 
			break;
		case UP: 
			break;
		case LEFT: 
			break;
		case RIGHT: 
			break;
		}
		return null;
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that direction is passable.
	 * The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.  
	 */
	public boolean equals(Square other) {
		throw new RuntimeException("Not yet implemented");
	}
	
}
