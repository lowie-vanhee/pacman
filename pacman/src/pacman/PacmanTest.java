package pacman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PacmanTest {

	@Test
	void test() {
		MazeMap map = new MazeMap(2,2,new boolean[] {true,true,false,true});
		
		assert map.isPassable(1,0) == false;
	}

}
