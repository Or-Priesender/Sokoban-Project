package model.data.level;

import model.data.files.Createable;

public class DestinationCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		return new Destination(p);
	}

}
