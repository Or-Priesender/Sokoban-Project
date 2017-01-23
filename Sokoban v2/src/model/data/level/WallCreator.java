package model.data.level;

import model.data.files.Createable;

public class WallCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		return new Wall(p);
	}

}
