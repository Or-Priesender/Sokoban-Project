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
import model.data.level.Level;
import model.data.level.LevelObject;
import model.policy.SokobanPolicy;

public class CLI extends Observable implements View{

	
	Displayable displayable;
	
	public void startCustomIO(InputStream in,OutputStream out)
	{
		Scanner scan = new Scanner(in);
		PrintStream writer = new PrintStream(out);
		//TODO : FIX SAVE !!
		
		Thread t = new Thread(new Runnable() {
			
			public void run() {
				while(true)
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

	@Override
	public void display(LevelObject[][] levelData) {
		
		displayable = new CharLevelDisplayer(levelData); 
		displayable.display();
		
	}

	@Override
	public void start() {
		
		startCustomIO(System.in, System.out);
		
	}
	

}
