package model.data.files;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

import model.data.level.Level;
import model.data.level.LevelObject;
import model.data.level.LevelObjectFactory;
import model.data.level.Player;
import model.data.level.Point2D;

public class MyTextLevelLoader implements LevelLoader {

	@Override    
	public Level loadLevel(InputStream in) throws IOException {
		
		
		
		Level newLevel = new Level();
		
		BufferedInputStream bi = new BufferedInputStream(in);
		BufferedReader r = new BufferedReader(new InputStreamReader(bi,StandardCharsets.UTF_8));
		
		LevelObjectFactory fac = new LevelObjectFactory();
		String line = null;
		LevelObject[][] map = null;
		
		int height=0;
		int width=0;
		int k=0;
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
		//line = r.readLine();
/*		if(line != null)
		{
			char[] size = line.toCharArray();
			
				while(size[k]!=' ')
				{
					height+=Character.getNumericValue(size[k]);
					k++;
					if(size[k]==' ')
						break;
					height*=10;
					
				}
				k++;
				while(size[k]!=' ')
				{
					width+=Character.getNumericValue(size[k]);
					k++;
					if(k==size.length)
						break;
					if(size[k]==' ')
						break;
					width*=10;
				}
			
			newLevel.setHeight(height);
			newLevel.setWidth(width);
			map = new LevelObject[height][width];
		}
		else return null;*/
		
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
					
					else if(seperated[j]=='o')
						newLevel.setDestinationCounter(newLevel.getDestinationCounter()+1);
					map[i][j]= fac.getObject(seperated[j],new Point2D(i,j));
			    }
		    }
			
		}
		
		newLevel.setMap(map);
		
		in.close();
		bi.close();
		r.close();
		
		
		//in the future : add algorithm to solve stage and determine steps,time etc.
		
				
	
		return newLevel;
	}

}
