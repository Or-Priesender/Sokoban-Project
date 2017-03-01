package model.policy;

import model.data.level.Level;

/*
 * General interface for policy. 
 */

public interface SokobanPolicy {
	
	public boolean Possible(Level lvl,String direction);
	

}
