package controller.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.AcceptPendingException;
import java.util.LinkedList;
import java.util.Observable;




public class MyServer extends Observable {
	
	private int port;
	private boolean stop;
	
	
	public MyServer(int port)
	{
		this.port=port;
		stop = false;
	}
	
	
	public void runServer() throws IOException{
		
		String commandKey;
		ServerSocket server = new ServerSocket(port);
		server.setSoTimeout(1000);
		Socket aClient = server.accept();
		BufferedReader b = new BufferedReader(new InputStreamReader(aClient.getInputStream()));
		while(!stop)
		{
			LinkedList<String> list= new LinkedList<String>();
			commandKey = b.readLine();
			list.addFirst(commandKey);
			notifyObservers(list);
		}
		server.close();
		
	}

	public void start(){
		new Thread(new Runnable(){
			public void run(){
				try{runServer(); } catch (IOException e){e.printStackTrace(); }
			}
		}).start();
	}

	public void stop()
	{
		stop=true;
	}
	
}
