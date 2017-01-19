package model.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Observable;

import model.policy.MySokobanPolicy;
import model.policy.SokobanPolicy;

public class MyModel extends Observable implements Model {

	Level lvl;
	SokobanPolicy p;

	public MyModel()
	{
		lvl=null;
		p=new MySokobanPolicy();
	}
	
	
	public MyModel(Level current, SokobanPolicy p) {
		this.lvl=current;
		this.p = p;
	}
	
	
	@Override
	public SokobanPolicy getPolicy()
	{
		return p;
	}
	
	@Override
	public Level getLevel() {
		
		return lvl;
	}

	@Override
	public boolean isFinished() {
		// TODO : check if level is finished
		return false;
	}
	public Level getCurrent() {
		return lvl;
	}
	public void setCurrent(Level current) {
		this.lvl = current;
	}


	@Override
	public void setPolicy(SokobanPolicy p) {
		this.p=p;
		
	}
	
	public void loadLevel(InputStream in,LevelLoader loader) throws ClassNotFoundException, IOException
	{
		lvl = loader.loadLevel(in);
		
		if(lvl != null)
		{
			LinkedList<String> params = new LinkedList<String>();
			params.add("display");
			this.setChanged();
			this.notifyObservers(params);
		}
	}

	@Override
	public void move(String direction) {
		if(p.Possible(lvl, direction))
		{
			Player player = lvl.getPlayer1();
			Point current = lvl.getPlayerPos();
			
			if(direction.compareToIgnoreCase("up")==0)
			{
				Point target = new Point(current.getY()-1,current.getX());
				Point dragged = new Point(current.getY()-2,current.getX());
				lvl.movePlayer(player, current, target, dragged);
			}
			else if(direction.compareToIgnoreCase("down")==0)
			{
				Point target = new Point(current.getY()+1,current.getX());
				Point dragged = new Point(current.getY()+2,current.getX());
				lvl.movePlayer(player, current, target, dragged);
			}
			else if(direction.compareToIgnoreCase("right")==0)
			{
				Point target = new Point(current.getY(),current.getX()+1);
				Point dragged = new Point(current.getY(),current.getX()+2);
				lvl.movePlayer(player, current, target, dragged);
			}
			else if(direction.compareToIgnoreCase("left")==0)
			{
				Point target = new Point(current.getY(),current.getX()-1);
				Point dragged = new Point(current.getY(),current.getX()-2);
				lvl.movePlayer(player, current, target, dragged);
			}
			
			this.setChanged();
			LinkedList<String> params = new LinkedList<String>();
			params.add("display");
			notifyObservers(params);
		}
	}


	@Override
	public LevelObject[][] getLevelData() {
		if(lvl != null)
			return lvl.getMap();
		else return null;
	}


	@Override
	public void saveLevel(OutputStream out, LevelSaver saver) throws IOException {
		
		if(lvl!=null){
			saver.saveLevel(lvl, out);
			this.setChanged();
			LinkedList<String> params = new LinkedList<String>();
			params.add("saved");
		}
		
	}

}
