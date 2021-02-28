package pacman;

import java.lang.Math;
import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * @immutable
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
		
		int rowOffset = 0;
		int colOffset = 0;
		
		switch(direction)
		{
		case DOWN:  rowOffset =  1; break;
		case UP:	rowOffset = -1; break;
		case RIGHT: colOffset =  1; break;
		case LEFT:  colOffset = -1; break;
		}
		
		int neighborRowIndex    = Math.floorMod(rowIndex + rowOffset,    mazeMap.getHeight());
		int neighborColumnIndex = Math.floorMod(columnIndex + colOffset, mazeMap.getWidth());
		
		return Square.of(this.mazeMap, neighborRowIndex, neighborColumnIndex);
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {
		
		Square neighbor = getNeighbor(direction);
		
		return neighbor.isPassable();
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that direction is passable.
	 * The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		
		Direction[] passableDirections = new Direction[4];
		
		int i = 0;
		
		for(Direction direction : Direction.values())
			if(direction != excludedDirection && getNeighbor(direction).isPassable()) passableDirections[i++] = direction; 
		
		return Arrays.copyOf(passableDirections, i);
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.  
	 */
	public boolean equals(Square other) {
		return (this.mazeMap == other.mazeMap && this.rowIndex == other.rowIndex && this.columnIndex == other.columnIndex);
	}
	
}
