package pacman.wormholes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

class WormholesTest {

	@Test
	void test() {
		
		boolean[] passable = {	true,  true,  true,
						  		false, false, true,
						  		true,  false, true,
						  		false, false, false};
		
		int mapWidth = 3;
		int mapHeight = 4;
					
		MazeMap map = new MazeMap(mapWidth, mapHeight, passable);
			
			
		ArrivalPortal arrival = new ArrivalPortal(Square.of(map, 1, 0));
		DeparturePortal departure = new DeparturePortal(Square.of(map, mapHeight-1, mapWidth-1));	
		
		Wormhole holeke = new Wormhole(departure, arrival);
		
		
	}

}
