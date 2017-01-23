package model.data.level;

import model.data.files.Createable;

public class PlayerCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		
		return new Player(p);
	}

}
