package pacman;

import java.util.stream.IntStream;

/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze
 * and, for each position in the maze, whether it is passable or not.
 * 
 * @immutable
 * 
 * public class invariants
 * 
 * @invar the width is always positive or zero 
 * 		  | getWidth() >= 0
 * @invar the height is always positive or zero
 * 		  | getHeight()>= 0
 */
public class MazeMap {
	
	/**
	 * private class invariants
	 * 
	 * @invar | passable != null
	 * @invar | passable.length == width*height
	 * @invar | (width >= 0) && (height >= 0)
	 * passable is in row-major order
	 */
	
	/**
	 * @representationObject
	 */
	private boolean[] passable;
	
	private final int width;
	private final int height;
	
	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 * @basic
	 */
	public int getWidth() { return width; }
	
	/**
	 * Returns the height (i.e. the number of rows) of this maze map.
	 * @basic
	 */
	public int getHeight() {return height; }
	
	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 * 
	 * @inspects | this
	 * 
	 * @throws IllegalArgumentException	if rowIndex is not between 0 and height
	 * 		   | rowIndex < 0 || rowIndex > getHeight()
	 * @throws IllegalArgumentException	if columnIndex is not between 0 and width
	 * 		   | columnIndex < 0 || columnIndex > getWidth()
	 * 
	 * @basic
	 */
	public boolean isPassable(int rowIndex, int columnIndex) { 
		
		if(rowIndex < 0 || rowIndex >= height)
			throw new IllegalArgumentException("The given rowIndex "+rowIndex+" must be between 0 and height");
		if(columnIndex < 0 || columnIndex >= width)
			throw new IllegalArgumentException("columnIndex must be between 0 and width");
		
		return passable[rowIndex*width+columnIndex];
	}
	
	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the passability of the maze positions in the first row of the maze). 
	 * 
	 * @throws IllegalArgumentException if passable is {@code null}
	 * 		   | passable == null
	 * @throws IllegalArgumentException if the length of the given passable array is not equal to width*height
	 * 		   | passable.length != width*height
	 * @throws IllegalArgumentException if width is negative
	 * 		   | width < 0
	 * @throws IllegalArgumentException if height is negative
	 * 		   | height < 0
	 * 
	 * @inspects | passable
	 * @post   | getWidth() == width
	 * @post   | getHeight() == height
	 * 
	 * @post   
	 * 		   | IntStream.range(0, getHeight()).allMatch(row ->
	 * 		   |	 IntStream.range(0, getWidth()).allMatch(col ->
	 * 		   |	 	isPassable(row,col) == passable[row*getWidth()+col]))
	 * 
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		
		if(passable == null)
			throw new IllegalArgumentException("The given passable array must not be null");
		
		if(width*height != passable.length)
			throw new IllegalArgumentException("The given boolean array must be the same length as width*height");
		
		if(width < 0)
			throw new IllegalArgumentException("The given width must not be negative");
		
		if(height < 0)
			throw new IllegalArgumentException("The given height must not be negative");
		
		this.passable = passable.clone();
		this.width = width;
		this.height = height;
	}
}
