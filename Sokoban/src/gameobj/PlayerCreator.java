package gameobj;

import levels.Point;

public class PlayerCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		
		return new Player(p);
	}

}
