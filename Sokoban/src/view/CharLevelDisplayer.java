package view;


import model.data.Box;
import model.data.Destination;
import model.data.Level;
import model.data.LevelObject;
import model.data.Player;
import model.data.Wall;

public class CharLevelDisplayer extends LevelDisplayer {

	public CharLevelDisplayer(Level myLvl)
	{
		super(myLvl);
	}
	@Override
	public void display() {
		
		for(int i=0;i<myLvl.getHeight();i++)
		{
			for(int j=0;j<myLvl.getWidth();j++) 
			{
				System.out.print(LevelObjectToChar(myLvl.getMap()[i][j]));
				
			}
			System.out.println();
			
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
