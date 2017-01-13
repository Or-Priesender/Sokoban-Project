package controller;

import java.io.IOException;

import model.data.Level;
import model.data.Player;
import model.data.Point;
import model.policy.SokobanPolicy;

public class MoveRightCommand implements Command {

	Level myLevel;
	SokobanPolicy p;
	
	public MoveRightCommand(Level myLevel,SokobanPolicy p)
	{
		this.myLevel=myLevel;
		this.p=p;
	}
	@Override
	public void execute() throws IOException {
		if(p.Possible(myLevel, "right")){
			
		Player player = myLevel.getPlayer1();
		Point current = myLevel.getPlayerPos();
		
		Point target = new Point(current.getY(),current.getX()+1);
		Point dragged = new Point(current.getY(),current.getX()+2);
		myLevel.movePlayer(player, current, target, dragged);
		}
	}

}
