package model.data.level;

/**
 * A bean that defines a sokoban player.
 * @author Or Priesender
 *
 */
public class Player extends LevelObject{
	
	public Player()
	{
		super(new Point2D(0,0));
	}
	public Player(Point pos) {
		super(pos);
	}

	
	
	
}
