package view;


import java.io.OutputStream;
import java.io.PrintStream;

import model.data.level.Box;
import model.data.level.Destination;
import model.data.level.LevelObject;
import model.data.level.Player;
import model.data.level.Wall;


/**
 * This class draws a level with characters to an OutputStream.
 * @author Or Priesender
 *
 */
public class CharLevelDisplayer extends LevelDisplayer {

	protected PrintStream writer;

	//if there is no given output stream, write to console
	public CharLevelDisplayer(LevelObject[][] levelData)
	{
		super(levelData);
		writer = new PrintStream(System.out);
	}
	//if there is a given output stream, write to it
	public CharLevelDisplayer(LevelObject[][] levelData,OutputStream out)
	{
		super(levelData);
		this.writer = new PrintStream(out);
	}
	//draws the level at the current position
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

	//internal function to convert LevelObject to Char
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
