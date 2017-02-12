package model.data.files;

import java.io.IOException;
import java.io.OutputStream;

import model.data.level.Box;
import model.data.level.Destination;
import model.data.level.Level;
import model.data.level.LevelObject;
import model.data.level.Player;
import model.data.level.Wall;

public class MyTextLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(Level lvl, OutputStream out) throws IOException {
		
		for(int i=0;i<lvl.getHeight();i++)
		{
			for(int j=0;j<lvl.getWidth();j++) 
			{
				out.write(LevelObjectToChar(lvl.getMap()[i][j]));
			}
			
		
			out.write(System.getProperty("line.separator").getBytes());
			
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
