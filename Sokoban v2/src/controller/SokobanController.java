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
import controller.commands.FinishedLevelCommand;
import controller.commands.LoadFileCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveFileCommand;
import controller.server.MyServer;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Model;
import view.CharLevelDisplayer;
import view.ClientHandler;
import view.View;

public class SokobanController implements Observer {
	
	View view;
	Model model;
	
	Controller controller;
	MyServer server;
	Map<String,Command> commands;
	
	IntegerProperty steps;
	IntegerProperty time;
	
	
	//controller has dependency on both view and model
	public SokobanController(View view,Model model) {
		
		this.view=view;
		this.model=model;
		controller = new Controller();
		
		//time = new SimpleIntegerProperty();
		//view.bindTime(time);
		
		steps = new SimpleIntegerProperty();
		view.bindSteps(steps);
		
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
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				steps.set(model.getSteps());
				
			}
		});
		
		}
	}
	
	public void startServer(ClientHandler handler,int port)
	{
		handler.addObserver(this);
		server = new MyServer(port,handler);
		server.start();
	}
	
	public void stopServer()
	{
		if(server != null)
			server.stop();
		
	}
	
	public void safeExit()
	{
		stopServer();
		if(controller != null)
			controller.stop();
		
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
	commands.put("finished", new FinishedLevelCommand(view));
	commands.put("exit", new ExitCommand(model,view,this));
	
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	
	}

}
