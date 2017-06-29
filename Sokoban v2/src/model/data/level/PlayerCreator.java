package model.data.level;

import model.data.files.Createable;

/**
 * Creates a player level object
 * @author Or Priesender
 *
 */
public class PlayerCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		
		return new Player(p);
	}

}
