package view;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import controller.commands.CommonModelViewCommand;
import controller.commands.DisplayLevelCommand;
import controller.commands.ExitCommand;
import controller.commands.LoadFileCommand;
import controller.commands.SaveFileCommand;
import javafx.beans.property.IntegerProperty;
import model.data.level.Level;
import model.data.level.LevelObject;
import model.policy.SokobanPolicy;

public class ClientHandler extends Observable implements View{

	boolean stop = false;
	Displayable displayable;
	OutputStream out;
	InputStream in;
	
	public void startCustomIO(InputStream in,OutputStream out)
	{
		this.out = out;
		this.in = in;
		
		Scanner scan = new Scanner(in);
		PrintStream writer = new PrintStream(out);
	
		
		Thread t = new Thread(new Runnable() {
			
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
		t.start();
		
			
			
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
		stop = true;
		
	}

	@Override
	public void display(LevelObject[][] levelData) {
		
		displayable = new CharLevelDisplayer(levelData,this.out); 
		displayable.display();
		
	}
	
	

	@Override
	public void displayFinished() {
		
		System.out.println("Level Finished !");
		
	}

	@Override
	public void bindSteps(IntegerProperty steps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindTime(IntegerProperty time) {
		// TODO Auto-generated method stub
		
	}

	
	

}
