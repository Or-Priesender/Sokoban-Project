package controller;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import controller.commands.Command;

/**
 * This controller can be used in any project. It executes general commands and blocks if the queue is empty.
 * It runs in a separate thread so its not blocking the program, and can be injected with commands during runtime.
 * @author Or Priesender
 *
 */

public class Controller {
	
	private boolean keepGoing;
	private BlockingQueue<Command> q;
	
	public Controller()
	{
		q = new ArrayBlockingQueue<Command>(100);
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
