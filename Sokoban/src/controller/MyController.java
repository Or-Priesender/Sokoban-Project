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
	String[] message;
	Command cmd;
	boolean keepGoing;
	
	BlockingQueue<Command> q;
	HashMap<String,Command> modelMap;
	HashMap<String,Command> viewMap;
	
	//controller has dependency on both view and model
	public MyController(View view,Model model) {
		this.view=view;
		this.model=model;
		String[] message = new String[10];
		keepGoing=true;
		
		modelMap = new HashMap<String,Command>();
		viewMap = new HashMap<String,Command>();
		
		//maybe the commands will get the model in the constructor? and if the command requires, get the second word of the request?
		
	}
	@Override
	public void update(Observable o, Object arg){
		
		int i=0;
		String line = (String) arg;
		Scanner s= new Scanner(line);
		while(s.hasNext())
		{
			message[i]=s.next();
			i++;
		}
		
		
			
		if ( o == model)
		{
			cmd = modelMap.get(message[0]);
			q.add(cmd);
			
		}
		else if (o== view )
		{
			cmd = viewMap.get(message[0]);
		}
			
		
		s.close();
		
		
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
	//this controller always runs in a new thread
	@Override
	public void start() {
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				while(keepGoing)
				{
					try {
						Command cmd = q.take();
						cmd.execute();
					} catch (InterruptedException | IOException e) {
					
						e.printStackTrace();
					}
				}
				
			}
			
		});
		t.start();
	}
	@Override
	public void stop() {
		
		keepGoing = false;
		
	}

}
