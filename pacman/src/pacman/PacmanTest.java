package pacman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class PacmanTest {
	
	@Test
	void test()
	{
		/* MazeMap */
		
		//passable is in row-major order
		boolean[] passable = {true,  true,  true,
							  false, false, true,
							  true,  false, true,
							  false, false, false};
		
		int mapWidth = 3;
		int mapHeight = 4;
						
		MazeMap map = new MazeMap(mapWidth, mapHeight, passable);
				
		assert map.getWidth() == mapWidth;
		assert map.getHeight() == mapHeight;
		
		//First method of checking the correctness of map.isPassable()
		assert IntStream.range(0, map.getHeight()).allMatch(row -> IntStream.range(0, map.getWidth()).allMatch(col -> map.isPassable(row,col) == passable[row*map.getWidth()+col]));
		
		//Second method
		int passableSize = map.getWidth() * map.getHeight();
		boolean[] retrievedPassable = new boolean[passableSize];
				
		for(int i = 0; i < passableSize; i++) {
			retrievedPassable[i] = map.isPassable(i/map.getWidth(), i % map.getWidth());
		}
		
		assert Arrays.equals(passable, retrievedPassable);
		
		/* Square */
		
		int squareRowIndex = 0;
		int squareColumnIndex = 2;
		
		Square square0 = Square.of(map, squareRowIndex, squareColumnIndex);
		
		assert square0.getRowIndex() == squareRowIndex;
		assert square0.getColumnIndex() == squareColumnIndex;
		assert square0.getMazeMap() == map;
		assert square0.isPassable() == map.isPassable(square0.getRowIndex(), square0.getColumnIndex());
		
		assert square0.getNeighbor(Direction.RIGHT).getColumnIndex() == 0  &&  square0.getNeighbor(Direction.RIGHT).getRowIndex() == square0.getRowIndex();
		
		//getNeighbor
		Square square1 = Square.of(map, 1, 0);
		
		Square leftNeighbor = square1.getNeighbor(Direction.LEFT);
		Square rightNeighbor = square1.getNeighbor(Direction.RIGHT);
		Square upNeighbor = square1.getNeighbor(Direction.UP);
		Square downNeighbor = square1.getNeighbor(Direction.DOWN);
		
		assert leftNeighbor.getColumnIndex() == 2 &&  leftNeighbor.getRowIndex() == square1.getRowIndex();
		assert rightNeighbor.getColumnIndex() == 1 && rightNeighbor.getRowIndex() == square1.getRowIndex();
		assert upNeighbor.getRowIndex() == 0 && upNeighbor.getColumnIndex() == square1.getColumnIndex();
		assert downNeighbor.getRowIndex() == 2 && downNeighbor.getColumnIndex() == square1.getColumnIndex();
		
		//canMove
		
		assert square1.canMove(Direction.LEFT) == true;
		assert square1.canMove(Direction.RIGHT) == false;
		assert square1.canMove(Direction.DOWN) == true;
		assert square1.canMove(Direction.UP) == true;
		
		//getPassableDirectionsExcept
		Direction[] passableDirections0 = square1.getPassableDirectionsExcept(Direction.LEFT);
		Direction[] passableDirections1 = square1.getPassableDirectionsExcept(Direction.RIGHT);
		
		assertArrayEquals(passableDirections0, new Direction[] {Direction.DOWN, Direction.UP});
		assertArrayEquals(passableDirections1, new Direction[] { Direction.DOWN, Direction.LEFT, Direction.UP});
		
		
		//equals
		Square square2 = Square.of(map, 2, 2);
		
		assert square2.equals(square1) == false;
		
		Square square3 = Square.of(map, 2, 2);
		assert square2.equals(square3);
		
		
		/* PacMan */
		
		int lives = 2;
		
		PacMan pacman = new PacMan(lives, square2);
		
		assert pacman.getNbLives() == lives;
		assert pacman.getSquare() == square2;
		
		pacman.setSquare(square3);
		
		assert pacman.getSquare() == square3;
		
		pacman.die();
		assert pacman.getNbLives() == --lives;
		pacman.die();
		assert pacman.getNbLives() == --lives;
		
		/* Dot */
		
		Dot dot = new Dot(square2);
		
		assert dot.getSquare() == square2;
		
		/* Ghost */
		
		Ghost ghost = new Ghost(square2, Direction.LEFT);
		
		assert ghost.getSquare() == square2;
		assert ghost.getDirection() == Direction.LEFT;
		
		ghost.setSquare(square3);
		assert ghost.getSquare() == square3;
		
		ghost.setDirection(Direction.UP);
		assert ghost.getDirection() == Direction.UP;
		
	}
	

}
