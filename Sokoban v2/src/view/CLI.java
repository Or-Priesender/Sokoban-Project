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
import controller.Command;
import controller.DisplayLevelCommand;
import controller.ExitCommand;
import controller.LoadFileCommand;
import controller.SaveFileCommand;
import model.data.Level;
import model.data.LevelObject;
import model.policy.SokobanPolicy;

public class CLI extends Observable implements View {

	
	
	
	
	
	public void start(InputStream in,OutputStream out)
	{
		Scanner scan = new Scanner(in);
		PrintStream writer = new PrintStream(out);
		
		Thread t = new Thread(new Runnable() {
			
			public void run() {
				while(true)
				{
					writer.print("Enter a command :");
					String line = scan.nextLine();
					String[] arr = line.split(" ");
					List<String> params = new LinkedList<String>();
					for(String s : arr)
						params.add(s);
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
		
		//maybe change level displayer to get leveldata to his constructor ? 
		//because he doesnt need to know all the level ! 
		
	}
	

}
