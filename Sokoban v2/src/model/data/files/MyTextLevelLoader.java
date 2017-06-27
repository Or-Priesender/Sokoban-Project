package model.data.files;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import model.data.level.Box;
import model.data.level.Destination;
import model.data.level.Level;
import model.data.level.LevelObject;
import model.data.level.LevelObjectFactory;
import model.data.level.Player;
import model.data.level.Point2D;

/*
 * Loads a text file with any InputStream
 */

public class MyTextLevelLoader implements LevelLoader {

	@Override    
	public Level loadLevel(InputStream in) throws IOException {
		
		
		
		Level newLevel = new Level();
		
		BufferedInputStream bi = new BufferedInputStream(in);
		BufferedReader r = new BufferedReader(new InputStreamReader(bi));
		
		LevelObjectFactory fac = new LevelObjectFactory();
		String line = null;
		
		LevelObject[][] map = null;
		
		int height=0;
		int width=0;
		
		String levelName = r.readLine();
		newLevel.setLevelName(levelName);
		
		r.mark(1000);
		do
		{
			line = r.readLine();
			if(line!=null){
				
				 if(width<line.length())
					width = line.length();
				height++;
			}
		}while(line != null);
		
		r.reset();

		
		newLevel.setHeight(height);
		newLevel.setWidth(width);
		map = new LevelObject[height][width];
		
		for (int i =0;i<newLevel.getHeight();i++)
		{
			line=r.readLine();
			if(line!=null)
			{
				char[] seperated = line.toCharArray();
				for(int j=0;j<seperated.length; j++)
				{
					if(fac.getObject(seperated[j], new Point2D(i,j)) instanceof Player)
					{
						newLevel.setPlayerPos(new Point2D(i,j));
						newLevel.setPlayer1((Player)fac.getObject(seperated[j], new Point2D(i,j)));
					}
					
					else if(seperated[j]=='o'){
						newLevel.setDestinationCounter(newLevel.getDestinationCounter()+1);
						
					}
					map[i][j] = fac.getObject(seperated[j],new Point2D(i,j));
					if(map[i][j] instanceof Box) newLevel.addBox((Box)map[i][j]);
					else if(map[i][j] instanceof Destination) newLevel.addDestination((Destination) map[i][j]);
			    }
		    }
			
		}
		
		newLevel.setMap(map);
		in.close();
		bi.close();
		r.close();
		return newLevel;
	}

}
