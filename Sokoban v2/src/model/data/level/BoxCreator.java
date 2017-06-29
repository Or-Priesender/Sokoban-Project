package model.data.level;

import model.data.files.Createable;

/**
 * Creates a box instance. Used for LevelObjectFactory class.
 * @author Or Priesender
 *
 */
public class BoxCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		return new Box(p);
		
	}

}
