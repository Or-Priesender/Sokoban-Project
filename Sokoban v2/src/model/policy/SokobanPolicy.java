package model.policy;

import model.data.level.Level;


/**
 * Defines a sokoban policy behavior.
 * @author Or Priesender
 *
 */
public interface SokobanPolicy {
	
	public boolean Possible(Level lvl,String direction);
	

}
