package model.data.files;

import model.data.level.LevelObject;
import model.data.level.Point;

public interface Createable {
	
	public LevelObject create(Point p);

}
