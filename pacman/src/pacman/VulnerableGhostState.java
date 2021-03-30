package pacman;

public class VulnerableGhostState extends GhostState{
	
	private VulnerableGhostState() {}
	public static final VulnerableGhostState INSTANCE = new VulnerableGhostState();
	
	@Override
	public boolean isVulnerable() {
		return true;
	}
}
