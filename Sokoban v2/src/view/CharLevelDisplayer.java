package view;


import java.io.OutputStream;
import java.io.PrintStream;

import model.data.level.Box;
import model.data.level.Destination;
import model.data.level.Level;
import model.data.level.LevelObject;
import model.data.level.Player;
import model.data.level.Wall;

public class CharLevelDisplayer extends LevelDisplayer {
	
	PrintStream writer;

	public CharLevelDisplayer(LevelObject[][] levelData)
	{
		super(levelData);
		writer = new PrintStream(System.out);
	}
	
	public CharLevelDisplayer(LevelObject[][] levelData,OutputStream out)
	{
		super(levelData);
		this.writer = new PrintStream(out);
	}
	@Override
	public void display() {
		
		
		writer.println();
	
		for(int i=0;i<levelData.length;i++)
		{
			for(int j=0;j<levelData[i].length;j++) 
			{
				writer.print(LevelObjectToChar(levelData[i][j]));
				
			}
			writer.println();
			
		}
		

	}
	
	private char LevelObjectToChar(LevelObject o)
	{
		if (o instanceof Box)
			return '@';
		else if(o instanceof Wall)
			return '#';
		else if(o instanceof Destination)
			return 'o';
		else if(o instanceof Player)
			return 'A';
		else return ' ';
	}

}
