package controller;


import model.data.Level;
import model.data.LevelObject;
import model.data.Model;
import model.data.Moveable;
import model.data.Player;
import model.data.Point;
import model.policy.MySokobanPolicy;
import model.policy.SokobanPolicy;

import java.awt.geom.Point2D;

public class MoveCommand extends Command {

	private Model model;
	
	
	public MoveCommand(Model model)
	{
		this.model = model;
	}
	//he just did move(direction) and thats it ? ? ?
	
	@Override
	public  void execute() {
		
		String direction = params.get(0);
		
		if(direction !=null)
			model.move(direction);
		
	}


	
}
