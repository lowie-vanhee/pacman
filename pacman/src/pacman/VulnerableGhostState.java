package pacman;

import java.util.Random;

public class VulnerableGhostState extends GhostState{
	
	private int moveDelay;
	private int reallyMovedCounter;
	public VulnerableGhostState() {moveDelay = 1; reallyMovedCounter = 0;}
	
	@Override
	public boolean isVulnerable() {
		return true;
	}
	
	@Override
	public GhostState move(Ghost ghost, Random random) {
		
		if(moveDelay == 0) {
			ghost.reallyMove(random);
			moveDelay = 1;
			reallyMovedCounter++;
		}
		else {
			moveDelay--;
		}
		if(reallyMovedCounter == 6) {
			return new RegularGhostState();
		}
		return this;
	}

	@Override
	public GhostState hitBy(Ghost ghost, PacMan pacMan) {
		
		ghost.setSquare(ghost.getOriginalSquare());
		
		return new RegularGhostState();
	}
}
