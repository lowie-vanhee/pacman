package pacman;

import java.lang.Math;
import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * @immutable
 * 
 * @invar the mazeMap is never {@code: null}
 * 		| getMazeMap() != null
 * @invar the rowIndex is always between 0 and the height of the mazeMap
 * 		| getRowIndex() >= 0 && getMazeMap().getHeight() >= getRowIndex()
 * @invar the columnIndex is always between 0 and the width of the mazeMap
 * 		| getColumnIndex() >= 0 && getMazeMap().getWidth() >= getColumnIndex()
 */
public class Square {
	
	/**
	 * @invar | mazeMap != null
	 * @RepresentationObject
	 */
	private MazeMap mazeMap;
	
	/**
	 * @invar | rowIndex >= 0 && mazeMap.getHeight() >= rowIndex
	 * @invar | columnIndex >= 0 && mazeMap.getWidth() >= columnIndex
	 */
	private int rowIndex;
	private int columnIndex;
	
	/**
	 * @basic
	 */
	public MazeMap getMazeMap() { return mazeMap; }
	
	/**
	 * @basic
	 */
	public int getRowIndex() { return rowIndex; }
	
	/**
	 * @basic
	 */
	public int getColumnIndex() { return columnIndex; }
	
	/**
	 * @post | result == getMazeMap().isPassable(getRowIndex(), getColumnIndex())
	 */
	public boolean isPassable() { return mazeMap.isPassable(rowIndex, columnIndex); }
	
	/**
	 * @throws IllegalArgumentException if mazeMap is null
	 * 		| mazeMap == null
	 * @throws IllegalArgumentException if rowIndex is negative
	 * 		| rowIndex < 0
	 * @throws IllegalArgumentException if columnIndex is negative
	 * 		| columnIndex < 0
	 * 
	 * @post The result is not {@code: null}
	 * 		| result != null
	 *
	 */
	//@post | result == 
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
	 * @throws IllegalArgumentException if other is {@code: null}
	 * 		| other == null 
	 * 
	 * @inspects | other
	 * 
	 * @post | result == (getMazeMap() == other.getMazeMap() && getRowIndex() == other.getRowIndex() && getColumnIndex() == other.getColumnIndex()) 
	 */
	public boolean equals(Square other) {
		return (this.mazeMap == other.mazeMap && this.rowIndex == other.rowIndex && this.columnIndex == other.columnIndex);
	}
	
}
