package controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.commands.Command;
import controller.commands.CommonModelViewCommand;

public class Controller {
	
	boolean keepGoing;
	BlockingQueue<Command> q;
	
	public Controller()
	{
		q = new ArrayBlockingQueue<Command>(20);
		keepGoing = true;
	
	}
	
	public void start()
	{
		
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				while(keepGoing)
				{
					try {
						Command c = q.poll(1, TimeUnit.SECONDS);
						if(c!=null){
						c.execute();
						}
					} catch (InterruptedException | IOException e) {
					
						e.printStackTrace();
					}
				}
				
			}
			
		});
		t.start();
		
	}
	public void stop()
	{
		keepGoing = false;
	}
	public void insertCommand(Command c)
	{
		if(c!=null)
			try {
				q.put(c);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
	}
	
	
}
