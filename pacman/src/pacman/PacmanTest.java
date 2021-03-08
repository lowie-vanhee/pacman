package pacman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PacmanTest {

	@Test
	void test() {
		
		//passable is in row-major order
		boolean[] passable = {true,  true,  true,
							  false, false, true,
							  true,  false, true,
							  false, false, false};
		
		MazeMap map = new MazeMap(3, 4, passable);
		
	}

}
