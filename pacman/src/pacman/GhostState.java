package pacman;

import java.util.Random;

public abstract class GhostState {
	
	private boolean isVulnerable = false;
	
	public boolean isVulnerable() {
		return false;
	}
	
	public abstract GhostState move(Ghost ghost, Random random);

	public abstract GhostState hitBy(Ghost ghost, PacMan pacMan);
}
