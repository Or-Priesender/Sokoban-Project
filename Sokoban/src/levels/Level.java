package levels;

import java.io.Serializable;
import java.util.HashMap;

import gameobj.Box;
import gameobj.Destination;
import gameobj.LevelObject;
import gameobj.Moveable;
import gameobj.MovingLevelObject;
import gameobj.Player;
import gameobj.Wall;
import policy.SokobanPolicy;

public class Level implements Serializable{

	
	LevelObject[][] map;
	int difficulty;
	int time;
	int steps;
	String levelName;
	int width;
	int height;
	Player player1;
	Point playerPos;
	
	public Level()
	{
		difficulty = 0;
		time = 0;
		steps = 0;
		levelName = " ";
		map = null;//check if null ruins something
		playerPos = new Point2D(0,0);
		player1=null;
	}
	
	private LevelObject getObjectFromMap(Point p)
	{
		return map[p.getY()][p.getX()];
	}
	
	private void setObjectToMap(LevelObject o,Point p)
	{
		if(map[p.getY()][p.getX()] instanceof Destination)
				o.setWasDestination(true);
		
				map[p.getY()][p.getX()] = o;
				o.setPosition(p);
				
				
		
	}
	//this function works only if your policy allows it
	public void movePlayer(Player p,Point current,Point target,Point draggedItem)
	{
		 
				//give a destination back to the array if we went over it
				if(p.isWasDestination())
				{
					setObjectToMap(new Destination(), current);
					p.setWasDestination(false);
				}
				else map[current.getY()][current.getX()] = null;
				//if we want to move a box
				if(getObjectFromMap(target) instanceof Box)
				{
					//if we are moving a box that is on a destination, now the player is on a destination
					if(getObjectFromMap(target).isWasDestination())
						p.setWasDestination(true);
					setObjectToMap(new Box(),draggedItem);
					
				}
				setPlayer1(p);
				setPlayerPos(target);
				setObjectToMap(p,target);
				
			
		
		
		
	}
	
	public LevelObject[][] getMap() {
		return map;
	}
	public void setMap(LevelObject[][] map) {
		this.map = map;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Point getPlayerPos() {
		return playerPos;
	}

	public void setPlayerPos(Point playerPos) {
		this.playerPos = playerPos;
	}
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	
	
	
	
}
