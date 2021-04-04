package pacman;

import java.util.Random;

public abstract class GhostState {
	
	private boolean isVulnerable = false;
	
	public boolean isVulnerable() {
		return false;
	}
	
	public GhostState move(Ghost ghost, Random random) {
		return null; //idk what to do here die man zen uitleg is fking vaag
	}

}
