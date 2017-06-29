package model.data.level;

import model.data.files.Createable;

/**
 * Creates a destination. Used for LevelObjectFactory class.
 * @author Or Priesender
 *
 */
public class DestinationCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		return new Destination(p);
	}

}
