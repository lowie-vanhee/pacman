package pacman.wormholes.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

class WormholesTest {

	@Test
	void test() {
		
		boolean[] passable = {	true,  true,  true, true,
						  		false, false, true, true,
						  		true,  false, true, false,
						  		false, false, true, true,
						  		false, true, false, true};
		
		int mapWidth = 4;
		int mapHeight = 5;
					
		MazeMap map = new MazeMap(mapWidth, mapHeight, passable);
		
		DeparturePortal d1 = new DeparturePortal(Square.of(map, mapHeight-1, mapWidth-1));	
		assertTrue(d1.getSquare().equals(Square.of(map, mapHeight-1, mapWidth-1)));
		assertEquals(d1.getWormholes(), Set.of());
		
		DeparturePortal d2 = new DeparturePortal(Square.of(map, 2, 2));	
		assertTrue(d2.getSquare().equals(Square.of(map, 2, 2)));
		assertEquals(d2.getWormholes(), Set.of());
		
		ArrivalPortal a1 = new ArrivalPortal(Square.of(map, 1, 0));
		assertTrue(a1.getSquare().equals(Square.of(map, 1, 0)));
		assertEquals(a1.getWormholes(), Set.of());
		
		ArrivalPortal a2 = new ArrivalPortal(Square.of(map, 3, 3));
		assertTrue(a2.getSquare().equals(Square.of(map, 3, 3)));
		assertEquals(a2.getWormholes(), Set.of());
		
		Wormhole w1 = new Wormhole(d1, a1);
		assertEquals(a1, w1.getArrivalPortal());
		assertEquals(d1, w1.getDeparturePortal());
		assertEquals(Set.of(w1), a1.getWormholes());
		assertEquals(Set.of(w1), d1.getWormholes());
		
		Wormhole w2 = new Wormhole(d1, a2);
		assertEquals(a2, w2.getArrivalPortal());
		assertEquals(d1, w2.getDeparturePortal());
		assertEquals(Set.of(w2), a2.getWormholes());
		assertEquals(Set.of(w2, w1), d1.getWormholes());
		
		Wormhole w3 = new Wormhole(d1, a2);
		assertEquals(a2, w3.getArrivalPortal());
		assertEquals(d1, w3.getDeparturePortal());
		assertEquals(Set.of(w3, w2), a2.getWormholes());
		assertEquals(Set.of(w3, w2, w1), d1.getWormholes());
		
		w2.setDeparturePortal(d2);
		assertEquals(d2, w2.getDeparturePortal());
		assertEquals(Set.of(w3, w1), d1.getWormholes());
		assertEquals(Set.of(w2), d2.getWormholes());
		
		w2.setArrivalPortal(a1);
		assertEquals(a1, w2.getArrivalPortal());
		assertEquals(Set.of(w3), a2.getWormholes());
		assertEquals(Set.of(w2, w1), a1.getWormholes());
		
		w2.setDeparturePortal(d1);
		assertEquals(d1, w2.getDeparturePortal());
		assertEquals(Set.of(w3, w2, w1), d1.getWormholes());
		assertEquals(Set.of(), d2.getWormholes());
	}

}
