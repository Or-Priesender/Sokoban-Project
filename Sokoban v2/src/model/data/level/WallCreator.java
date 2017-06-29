package model.data.level;

import model.data.files.Createable;

/**
 * Creates a wall object. Used for the LevelObjectFactory class.
 * @author Or Priesender
 *
 */
public class WallCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		return new Wall(p);
	}

}
