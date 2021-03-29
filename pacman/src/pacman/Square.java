package pacman;

import java.lang.Math;
import java.util.stream.IntStream;
import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * @immutable
 * 
 * public class invariants:
 * 
 * @invar the mazeMap is never {@code null} | getMazeMap() != null
 * 
 * @invar the rowIndex is always between 0 and the height of the mazeMap 
 * 		  | getRowIndex() >= 0 && getRowIndex() < getMazeMap().getHeight()
 * @invar the columnIndex is always between 0 and the width of the mazeMap 
 * 		  | getColumnIndex() >= 0 && getColumnIndex() < getMazeMap().getWidth()
 */
public class Square {
	
	/**
	 * private class invariants:
	 * 
	 * @invar | mazeMap != null
	 * 
	 * @invar | rowIndex >= 0 && rowIndex < mazeMap.getHeight()
	 * @invar | columnIndex >= 0 && columnIndex < mazeMap.getWidth()
	 */
	
	private MazeMap mazeMap;
	
	private int rowIndex;
	private int columnIndex;
	
	/**
	 * Returns the (immutable) MazeMap object of which this square is part of
	 * @basic
	 */
	public MazeMap getMazeMap() { return mazeMap; }
	
	/**
	 * Returns this square's row index
	 * @basic
	 */
	public int getRowIndex() { return rowIndex; }
	
	/**
	 * Returns this square's column index
	 * @basic
	 */
	public int getColumnIndex() { return columnIndex; }
	
	/**
	 * Returns whether this square is passable in the square's corresponding maze map
	 * @inspects | this
	 * @post Returns whether this square is passable in the corresponding maze map | result == getMazeMap().isPassable(getRowIndex(), getColumnIndex())
	 */
	public boolean isPassable() { return mazeMap.isPassable(rowIndex, columnIndex); }
	
	/**
	 * Constructs a square object of the given maze map at the given row index and column index
	 * @creates | result
	 * 
	 * @throws IllegalArgumentException if mazeMap is {@code null} | mazeMap == null
	 * @throws IllegalArgumentException if rowIndex is negative or not less than the mazeMap's height| rowIndex < 0 || rowIndex >= mazeMap.getHeight()
	 * @throws IllegalArgumentException if columnIndex is negative or not less than the mazeMap's width| columnIndex < 0 || columnIndex >= mazeMap.getWidth()
	 * 
	 * @post | result.getMazeMap() == mazeMap
	 * @post | result.getRowIndex() == rowIndex
	 * @post | result.getColumnIndex() == columnIndex
	 * @post | result != null
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		
		if(mazeMap == null)
			throw new IllegalArgumentException("MazeMap cannot be null");
		if(rowIndex < 0 || rowIndex >= mazeMap.getHeight())
			throw new IllegalArgumentException("rowIndex must be between 0 and the height of the mazeMap");
		if(columnIndex < 0 || columnIndex >= mazeMap.getWidth())
			throw new IllegalArgumentException("columnIndex must be between 0 and the width of the mazeMap");
		
		Square square = new Square(mazeMap, rowIndex, columnIndex);
		
		return square;
	}
	
	/**
	 * Constructor marked as private as client should use Square.of(...) method for retrieving an (immutable) Square object, instead of calling the constructor directly
	 */
	private Square(MazeMap mazeMap, int rowIndex, int columnIndex) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.mazeMap = mazeMap;
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
	 * @inspects | this, other
	 * 
	 * @throws if other is null | other == null
	 * 
	 * @post | result == (this.getMazeMap() == other.getMazeMap() && this.getRowIndex() == other.getRowIndex() && this.getColumnIndex() == other.getColumnIndex())
	 */
	public boolean equals(Square other) {
		if(other == null)
			throw new IllegalArgumentException("other cannot be null");
		
		return (this.mazeMap == other.mazeMap && this.rowIndex == other.rowIndex && this.columnIndex == other.columnIndex);
	}
	
}
