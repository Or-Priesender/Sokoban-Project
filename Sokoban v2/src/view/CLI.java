package controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.data.Level;
import model.policy.SokobanPolicy;
import view.CharLevelDisplayer;

public class CLI extends CommonInvoker implements Command {
	
	Command command;
	Scanner scan;
	String[] args;
	Level current;
	SokobanPolicy p;
	
	public CLI(SokobanPolicy p)
	{
		
		args = new String[5];
		this.p = p;
		current = null;
	}
	
	public void execute()
	{
		//in the future : do whatever you need to do before exiting, right now its nothing
		System.exit(0);
	}
	
	public void userInterface() throws IOException
	{
		
		while(true)
		{
			int i=0;
			System.out.print("Enter a command :");
			BufferedReader b= new BufferedReader(new InputStreamReader(System.in));
			String line = b.readLine();
			scan = new Scanner(line);
			
			while(scan.hasNext())
			{
				args[i] = scan.next();
				i++;
				
				
			}
			
			if(args[0].compareToIgnoreCase("move")==0)
			{
				if(current!=null){
				if(args[1].compareToIgnoreCase("right")==0)
				{
					command = new MoveRightCommand(current,p);
				}
				else if(args[1].compareToIgnoreCase("left")==0)
				{
					command = new MoveLeftCommand(current,p);
				}
				else if(args[1].compareToIgnoreCase("up")==0)
				{
					command = new MoveUpCommand(current,p);
				}
				else if(args[1].compareToIgnoreCase("down")==0)
				{
					command = new MoveDownCommand(current,p);
				}
				}
				/*
				if(current != null){
					command = new MoveCommand(args[1],current,p);
					command.execute();
					command = new DisplayLevelCommand(new CharLevelDisplayer(current));
					command.execute();
					
				}	
				*/
				
				else System.out.println("Level is empty");
				
			}
			else if(args[0].compareToIgnoreCase("load")==0)
			{
				if(current!=null)
				{
					command = new SaveFileCommand("defaultSaver.obj", current);
					current = null;
				}
				command = new LoadFileCommand(args[1], current);
				command.execute();
				current = ((LoadFileCommand)command).getLvl();  //is down cast necessary? //
				System.out.println("Level Loaded.");
			}
			else if(args[0].compareToIgnoreCase("display")==0)
			{
				if(current == null)
					System.out.println("Nothing to display");
				else 
				{
					command = new DisplayLevelCommand(new CharLevelDisplayer(current));
					command.execute();
				}
			}
			else if(args[0].compareToIgnoreCase("save")==0)
			{
				if(current == null)
					System.out.println("Nothing to save");
				else
				{
					command = new SaveFileCommand(args[1], current);
					command.execute();
					System.out.println("Level saved.");
				}
			}
			else if(args[0].compareToIgnoreCase("exit")==0)
			{
				command = new ExitCommand(this);
				System.out.println("Exiting...");
				
				command.execute();
			}
			args = new String[args.length];
		}
	}
	

}
