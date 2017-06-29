package model.data.level;

/**
 * Bean to define a sokoban Destination.
 * @author Or Priesender
 *
 */
public class Destination extends LevelObject {
	
	public Destination()
	{
		super(new Point2D(0,0));
	}

	public Destination(Point pos) {
		super(pos);
	}
}
