package model.data;

import model.policy.SokobanPolicy;

public interface Model {

	public Level getLevel();
	public boolean isFinished();
	public SokobanPolicy getPolicy();
	public void setPolicy(SokobanPolicy p);
	
}
