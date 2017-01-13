package model.data;

public class BoxCreator implements Createable {

	@Override
	public LevelObject create(Point p) {
		return new Box(p);
		
	}

}
