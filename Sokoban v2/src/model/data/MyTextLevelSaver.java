package model.data;

import java.io.IOException;
import java.io.OutputStream;

public class MyTextLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(Level lvl, OutputStream out) throws IOException {
		
		for(int i=0;i<lvl.getHeight();i++)
		{
			for(int j=0;j<lvl.getWidth();j++) 
			{
				out.write(LevelObjectToChar(lvl.getMap()[i][j]));
			}
			//works only in windows! to do : find better solution
			out.write('\r');
			out.write('\n');
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
