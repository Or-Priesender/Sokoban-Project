package controller;

import java.io.IOException;

import model.data.Level;
import model.data.Player;
import model.data.Point;
import model.policy.SokobanPolicy;

public class MoveDownCommand implements Command {

	Level myLevel;
	SokobanPolicy p;

	public MoveDownCommand(Level myLevel,SokobanPolicy p)
	{
		this.myLevel=myLevel;
		this.p=p;
	}
	
	@Override
	public void execute() throws IOException {
		
		if(p.Possible(myLevel, "down")){
		
		Player player = myLevel.getPlayer1();
		Point current = myLevel.getPlayerPos();
		
		
			Point target = new Point(current.getY()+1,current.getX());
			Point dragged = new Point(current.getY()+2,current.getX());
			myLevel.movePlayer(player, current, target, dragged);
			
		
		}
	}

}
