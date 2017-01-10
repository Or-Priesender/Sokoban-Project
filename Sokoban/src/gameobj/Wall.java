package gameobj;

import levels.Point;
import levels.Point2D;

public class Wall extends LevelObject {

	
	public Wall()
	{
		super(new Point2D(0,0));
	}
	public Wall(Point pos) {
		super(pos);
	}
}
