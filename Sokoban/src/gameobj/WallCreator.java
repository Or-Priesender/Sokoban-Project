package gameobj;

import levels.Point;

public class WallCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		return new Wall(p);
	}

}
