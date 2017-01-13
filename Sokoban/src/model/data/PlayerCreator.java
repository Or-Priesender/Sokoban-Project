package model.data;

public class PlayerCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		
		return new Player(p);
	}

}
