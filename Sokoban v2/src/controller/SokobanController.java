package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.commands.Command;
import controller.commands.CommonModelViewCommand;
import controller.commands.DisplayLevelCommand;
import controller.commands.ExitCommand;
import controller.commands.LoadFileCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveFileCommand;
import controller.server.MyServer;
import model.Model;
import view.CharLevelDisplayer;
import view.View;

public class SokobanController implements Observer {
	
	View view;
	Model model;
	
	Controller controller;
	MyServer server;
	Map<String,Command> commands;
	
	
	//controller has dependency on both view and model
	public SokobanController(View view,Model model) {
		
		this.view=view;
		this.model=model;
		controller = new Controller();
		
		commands = new HashMap<String,Command>();
		initCommands();
		//starts the controller in a new thread ! 
		controller.start();
		
		
	}
	@Override
	public void update(Observable o, Object arg){
		
		LinkedList<String> params = (LinkedList<String>) arg;
		String commandKey = params.removeFirst();
		Command c = commands.get(commandKey);
		if(c!=null){
		c.setParams(params);		
		controller.insertCommand(c);
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
	
	protected void initCommands(){
		
		try {
	commands.put("move", new MoveCommand(model));
	commands.put("display", new DisplayLevelCommand(model,view));
	commands.put("load", new LoadFileCommand(model));
	commands.put("save", new SaveFileCommand(model));
	commands.put("exit", new ExitCommand(model,view));
	
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	
	}

}
