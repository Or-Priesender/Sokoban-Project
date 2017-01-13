package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import model.data.Model;
import view.CharLevelDisplayer;
import view.View;

public class MyController implements Controller {

	View view;
	Model model;
	Command command;
	BlockingQueue<Command> q;
	HashMap<String,Command> map;
	//controller has dependency on both view and model
	public MyController(View view,Model model) {
		this.view=view;
		this.model=model;
		map = new HashMap<String,Command>();
		
		map.put("level changed", new MoveUpCommand(model.getLevel(),model.getPolicy()));
		
		
		map.put("display", new DisplayLevelCommand(new CharLevelDisplayer(model.getLevel())));
	}
	@Override
	public void update(Observable o, Object arg){
		
		
			
		if ( o == model)
		{
			
			
		}
		else if (o== view )
		{
			
		}
			
		
		
		
		
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
