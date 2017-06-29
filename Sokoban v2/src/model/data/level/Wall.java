package model.data.level;

/**
 * This bean defines a sokoban level wall.
 * @author Or Priesender
 *
 */
public class Wall extends LevelObject {

	
	public Wall()
	{
		super(new Point2D(0,0));
	}
	
	public Wall(Point pos) {
		super(pos);
	}
}
