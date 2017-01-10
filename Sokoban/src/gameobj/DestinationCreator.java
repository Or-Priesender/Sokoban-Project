package gameobj;

import levels.Point;

public class DestinationCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		return new Destination(p);
	}

}
