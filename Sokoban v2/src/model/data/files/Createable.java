package model.data.files;

import model.data.level.LevelObject;
import model.data.level.Point;

/**
 * Defines a creatable object behavior.
 * @author Or Priesender
 *
 */
public interface Createable {
	
	public LevelObject create(Point p);

}
