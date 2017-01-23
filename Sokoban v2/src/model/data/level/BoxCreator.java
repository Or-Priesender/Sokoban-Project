package model.data.level;

import model.data.files.Createable;

public class BoxCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		return new Box(p);
		
	}

}
