package model.policy;




import model.data.Box;
import model.data.Destination;
import model.data.Level;
import model.data.LevelObject;
import model.data.Point;
import model.data.Wall;

public class MySokobanPolicy implements SokobanPolicy {

	boolean levelChanged;
	
	@Override//this function checks if it is possible to move to the desired location
	public boolean Possible(Level lvl, String direction) {
	if (lvl != null){
	Point	p=lvl.getPlayerPos();
	LevelObject[][] map = lvl.getMap();
		//player wants to move up
		if(direction.compareToIgnoreCase("up")==0)
		{
			
			
			
			
			//in case player wants to go in a wall
			if(map[p.getY()-1][p.getX()] instanceof Wall)
			{
				return false;
			}
			//in case player wants to move up and move a box up as well
			else if(map[p.getY()-1][p.getX()] instanceof Box)
			{
				//case there is another box above that box
				if(map[p.getY()-2][p.getX()] instanceof Box)
					return false;
				//case there is a wall above the box
				else if(map[p.getY()-2][p.getX()] instanceof Wall)
					return false;
				//case there is a destination above the box 
				else if(map[p.getY()-2][p.getX()] instanceof Destination)
				{
					return true;
				}
				else if(map[p.getY()-2][p.getX()] == null)
				{
					return true;
				}
			}
			// case the player wants to go over a destination
			else if(map[p.getY()-1][p.getX()] instanceof Destination)
			{
				return true;
			}
			//case there is nothing above the player
			else if(map[p.getY()-1][p.getX()] == null)
			{
				return true;
			}
		}
			//if player wants to move down
		else if(direction.compareToIgnoreCase("down")==0)
			{
				
				
				
				 
				//in case player wants to go in a wall
				if(map[p.getY()+1][p.getX()] instanceof Wall)
					return false;
				//in case player wants to move down and move a box down as well
				else if(map[p.getY()+1][p.getX()] instanceof Box)
				{
					//case there is another box below that box
					if(map[p.getY()+2][p.getX()] instanceof Box)
						return false;
					//case there is a wall below the box
					else if(map[p.getY()+2][p.getX()] instanceof Wall)
						return false;
					//case there is a destination below the box 
					else if(map[p.getY()+2][p.getX()] instanceof Destination)
					{
						return true;
					}
					//case there is nothing below the box
					else if(map[p.getY()+2][p.getX()] == null)
					{
						return true;
					}
				}
				// case the player wants to go down over a destination
				else if(map[p.getY()+1][p.getX()] instanceof Destination)
				{
					return true;
				}
				//case there is nothing below the player
				else if(map[p.getY()+1][p.getX()] == null)
				{
					return true;
				}

			}
			
			//case player wants to go left
		else if(direction.compareToIgnoreCase("left")==0)
			{
				
				
				
				
				//in case player wants to go in a wall
				if(map[p.getY()][p.getX()-1] instanceof Wall)
					return false;
				//in case player wants to left and move a box left as well
				else if(map[p.getY()][p.getX()-1] instanceof Box)
				{
					//case there is another box to the left of that box
					if(map[p.getY()][p.getX()-2] instanceof Box)
						return false;
					//case there is a wall to the left of the box
					else if(map[p.getY()][p.getX()-2] instanceof Wall)
						return false;
					//case there is a destination to the left of the box 
					else if(map[p.getY()][p.getX()-2] instanceof Destination)
					{
						return true;
					}
					//case there is nothing to the left of the box
					else if(map[p.getY()][p.getX()-2] == null)
					{
						return true;
					}
				}
				// case the player wants to go over a destination
				else if(map[p.getY()][p.getX()-1] instanceof Destination)
				{
					return true;
				}
				//case there is nothing to the left of the player
				else if(map[p.getY()][p.getX()-1] == null)
				{
					return true;
				}
			
			}
			
			
		
		
		else if(direction.compareToIgnoreCase("right")==0)
		{
			
			
			
			
			//in case player wants to go in a wall
			if(map[p.getY()][p.getX()+1] instanceof Wall)
				return false;
			//in case player wants to move right and move a box right as well
			else if(map[p.getY()][p.getX()+1] instanceof Box)
			{
				//case there is another box to the right of that box
				if(map[p.getY()][p.getX()+2] instanceof Box)
					return false;
				//case there is a wall to the right of that box
				else if(map[p.getY()][p.getX()+2] instanceof Wall)
					return false;
				//case there is a destination to the right of that box 
				else if(map[p.getY()][p.getX()+2] instanceof Destination)
				{
					return true;
				}
				//case there is nothing to the right of that box
				else if(map[p.getY()][p.getX()+2] == null)
				{
					return true;
				}
			}
			// case the player wants to go over a destination
			else if(map[p.getY()][p.getX()+1] instanceof Destination)
			{
				return true;
			}
			//case there is nothing to the right of the player
			else if(map[p.getY()][p.getX()+1] == null)
			{
				return true;
			}
		}
	}
		 return false;
	}

	

}
