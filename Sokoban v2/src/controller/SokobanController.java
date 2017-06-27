package controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.commands.AlertCommand;
import controller.commands.Command;
import controller.commands.DBCommand;
import controller.commands.DisplayLevelCommand;
import controller.commands.ExitCommand;
import controller.commands.FinishedLevelCommand;
import controller.commands.LoadFileCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveFileCommand;
import controller.commands.SolutionCommand;
import controller.commands.UpdateTimeCommand;
import controller.server.MyServer;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Model;
import view.ClientHandler;
import view.View;

public class SokobanController implements Observer {
	
	/*
	 * Controls the model and view layers, and has the ability to run with a server.
	 */
	
	private View view;
	private Model model;
	
	private Controller controller;
	private MyServer server;
	private Map<String,Command> commands;
	
	private IntegerProperty steps;
	
	
	
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
		//arg is a linked list containing a String that will extract the right command
		LinkedList<String> params = (LinkedList<String>) arg;
		String commandKey = params.removeFirst();
		Command c = commands.get(commandKey);
		if(c!=null){
		c.setParams(params);
		controller.insertCommand(c);
		//needed to avoid Thread collision(JavaFx)
		Platform.runLater(()->steps.set(model.getSteps()));
		}
	}
	
	//starts the server with any ClientHandler
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
	//stops all open threads created by this class
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
	//initialize the HashMap
	protected void initCommands(){
		
		try {
	commands.put("time", new UpdateTimeCommand(model));
	commands.put("move", new MoveCommand(model));
	commands.put("display", new DisplayLevelCommand(model,view));
	commands.put("load", new LoadFileCommand(model));
	commands.put("save", new SaveFileCommand(model));
	commands.put("finished", new FinishedLevelCommand(view));
	commands.put("message", new AlertCommand(view));
	commands.put("DB", new DBCommand(model,view));
	commands.put("getSolution", new SolutionCommand(model,view));
	commands.put("exit", new ExitCommand(model,view,this));
	
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	
	}

}
