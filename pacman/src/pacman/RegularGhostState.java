package pacman;

import java.util.Random;

public class RegularGhostState extends GhostState{
	
	public RegularGhostState() {}

	@Override
	public GhostState move(Ghost ghost, Random random) {
		ghost.reallyMove(random);
		return this;
	}

	@Override
	public GhostState hitBy(Ghost ghost, PacMan pacMan) {
		pacMan.die();
		return this;
	}

}
