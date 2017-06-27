package view;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import javafx.beans.property.IntegerProperty;
import model.data.database.GameSession;
import model.data.level.LevelObject;

/*
 * This class gets commands from the client and pass it forward to the controller.
 * It can use custom output and input.
 */

public class ClientHandler extends Observable implements View{

	protected boolean stop = false;
	protected Displayable displayable;
	protected OutputStream out;
	protected InputStream in;
	protected Scanner scan;
	protected Thread thread;
	PrintStream writer;
	
	
	public ClientHandler()
	{
		this.in = System.in;
		this.out = System.out;
		writer = new PrintStream(System.out);
	}
	
	public void startCustomIO(InputStream in,OutputStream out)
	{
		this.out = out;
		this.in = in;
		
		scan = new Scanner(in);
		PrintStream writer = new PrintStream(out);
	
		
		thread = new Thread(new Runnable() {
			
			public void run() {
				while(!stop)
				{
					writer.print("Enter a command :");
					String line = scan.nextLine();
					String[] arr = line.split(" ");
					List<String> params = new LinkedList<String>();
					for(String s : arr)
					{
						
						params.add(s);
						
					}
					setChanged();
					notifyObservers(params);
					if(line.equals("exit"))
						break;
				}
				
			}
		});
		thread.start();
		
			
			
	}
	public void setOutputStream(OutputStream out)
	{
		this.out = out;
	}
	public void setInputStream(InputStream in)
	{
		this.in = in;
	}
	
	public void stop()
	{
		try {
			out.close();
			in.close();
			stop = true;
			
			if(thread!=null)
				thread.join();
			
			
			if(scan!=null)
			scan.close();
			
			
		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void display(LevelObject[][] levelData) {
		
		displayable = new CharLevelDisplayer(levelData,this.out); 
		displayable.display();
		
	}
	
	

	@Override
	public void displayFinished() {
		
		PrintStream writer = new PrintStream(out);
		writer.println("Level Finished ! ");
		
	}

	@Override
	public void bindSteps(IntegerProperty steps) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void displayRecord(int record) {
		
		writer.println("Record: "+record);
		
	}
	@Override
	public void serverStatus(boolean status) {
		if(status){
		writer.println("Server is on");
		}
		else 
			writer.println("Server is off");
	}

	@Override
	public void displayAlert(String title, String content) {
		
		writer.println(title);
		writer.println(content);
		
	}
	
	@Override
	public void displaySessionsList(List list) {
		
		List<GameSession> sessions = (List<GameSession>) list;
		for(GameSession l : sessions)
			System.out.println(l.getUsername() + " " + l.getLevelName() + " " + l.getSteps() + " " + l.getTime());
		
	}
	
	@Override
	public void getUserDetails() {
		
		System.out.println("please insert username:");
		Scanner s = new Scanner(in);
		String username = "NA";
		
		if(s.hasNext())
			username = s.next();
		LinkedList<String> params = new LinkedList<String>();
		params.add("saveToDB");
		params.add(username);
		setChanged();
		notifyObservers(params);
	}
	
	@Override
	public void getCurrentLevelSession() {
		
		LinkedList<String> params = new LinkedList<String>();
		params.add("LoadSessionDB");
		setChanged();
		notifyObservers(params);
		
	}

	@Override
	public void displaySolution(String solution) {
		//TODO: client handler display solution
	}

	@Override
	public void displayHint(String solution) {
		// TODO: client handler display hint
		
	}

	
	

}
