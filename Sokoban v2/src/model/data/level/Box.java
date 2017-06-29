package model.data.level;

/**
 * Bean to define a sokoban box.
 * @author Or Priesender
 *
 */
public class Box extends LevelObject{

	public Box(Point pos) {
		super(pos);
	}
	public Box()
	{
		super(new Point2D(0,0));
	}
	
	
}
