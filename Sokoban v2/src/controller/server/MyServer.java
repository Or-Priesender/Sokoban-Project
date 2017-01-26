package controller.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import view.ClientHandler;


public class MyServer extends Observable {
	
	private int port;
	private boolean stop;
	private ClientHandler handler;
	Thread thread;
	
	
	public MyServer(int port,ClientHandler Observedhandler)
	{
		this.handler = Observedhandler;
		this.port=port;
		stop = false;
	}
	
	
	public void runServer() throws IOException{
		
		
		ServerSocket server = new ServerSocket(port);
		Socket aClient = server.accept();
		
		//the handler continues until it is stopped
		handler.startCustomIO(aClient.getInputStream(), aClient.getOutputStream());
		
		server.close();
	}

	public void start(){
		 thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try { runServer(); }  catch (IOException e) { e.printStackTrace(); }
					
			}
		});
		 thread.start();
			
	}

	public void stop()
	{
		try {
			//first stop the handler and then wait for the thread to die
			handler.stop();
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
