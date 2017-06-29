package controller.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import view.ClientHandler;

/**
 * This class is a backdoor server which requires an observed client handler. It allows a remote user
 * to connect and play the game from the command line.
 * @author Or Priesender
 *
 */

public class MyServer extends Observable {
	
	private int port;
	private ClientHandler handler;
	private Thread thread;
	
	//the server must get a port to listen and an observed client handler.
	public MyServer(int port,ClientHandler Observedhandler)
	{
		this.handler = Observedhandler;
		this.port=port;
		
	}
	
	
	public void runServer() throws IOException{
		
		
		ServerSocket server = new ServerSocket(port);
		server.setSoTimeout(100000);
		Socket aClient = server.accept();
		
		
		//the handler continues until it is stopped
		handler.startCustomIO(aClient.getInputStream(), aClient.getOutputStream());
		
		server.close();
	}
	//makes the runServer() method run in a thread
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
