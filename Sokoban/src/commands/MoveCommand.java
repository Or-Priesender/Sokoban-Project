package commands;

import levels.*;
import java.awt.geom.Point2D;

import gameobj.LevelObject;
import gameobj.Moveable;
import gameobj.Player;
import levels.Level;
import policy.MySokobanPolicy;
import policy.SokobanPolicy;

public class MoveCommand implements Command {

	Level myLevel;
	String direction;
	SokobanPolicy p;
	
	
	public MoveCommand(String direction,Level lvl, SokobanPolicy p)
	{
		this.direction=direction;
		myLevel = lvl;
		this.p=p;
		//in case we want to change policy, we simply change p.
		
	
	}
	
	
	@Override
	public void execute() {
		if(p.Possible(myLevel, direction))
		{
			Player player = myLevel.getPlayer1();
			Point current = myLevel.getPlayerPos();
			
			if(direction.compareToIgnoreCase("up")==0)
			{
				Point target = new Point(current.getY()-1,current.getX());
				Point dragged = new Point(current.getY()-2,current.getX());
				myLevel.movePlayer(player, current, target, dragged);
			}
			else if(direction.compareToIgnoreCase("down")==0)
			{
				Point target = new Point(current.getY()+1,current.getX());
				Point dragged = new Point(current.getY()+2,current.getX());
				myLevel.movePlayer(player, current, target, dragged);
			}
			
			else if(direction.compareToIgnoreCase("left")==0)
			{
				Point target = new Point(current.getY(),current.getX()-1);
				Point dragged = new Point(current.getY(),current.getX()-2);
				myLevel.movePlayer(player, current, target, dragged);
			}
			else if(direction.compareToIgnoreCase("right")==0)
			{
				Point target = new Point(current.getY(),current.getX()+1);
				Point dragged = new Point(current.getY(),current.getX()+2);
				myLevel.movePlayer(player, current, target, dragged);
			}
			
			
			
		}
		

	}


	public Level getMyLevel() {
		return myLevel;
	}


	public void setMyLevel(Level myLevel) {
		this.myLevel = myLevel;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public SokobanPolicy getP() {
		return p;
	}


	public void setP(SokobanPolicy p) {
		this.p = p;
	}

}
