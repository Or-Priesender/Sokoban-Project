package controller.commands;


import model.Model;
import model.data.level.Level;
import model.data.level.LevelObject;
import model.data.level.Moveable;
import model.data.level.Player;
import model.data.level.Point;
import model.policy.MySokobanPolicy;
import model.policy.SokobanPolicy;

import java.awt.geom.Point2D;

public class MoveCommand extends CommonModelCommand {

	
	
	
	public MoveCommand(Model model)
	{
		this.model = model;
	}
	//he just did move(direction) and thats it ? ? ?
	
	@Override
	public  void execute() {
		
		String direction = params.removeFirst();
		
		if(direction !=null)
			model.move(direction);
		
	}


	
}
