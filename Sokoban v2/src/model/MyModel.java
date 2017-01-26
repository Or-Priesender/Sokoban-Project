package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Observable;



import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import model.data.files.LevelLoader;
import model.data.files.LevelSaver;
import model.data.files.MyObjectLevelSaver;
import model.data.level.Level;
import model.data.level.LevelObject;
import model.data.level.Player;
import model.data.level.Point;

import model.policy.MySokobanPolicy;
import model.policy.SokobanPolicy;

public class MyModel extends Observable implements Model {

	private Level lvl;
	private SokobanPolicy p;
	private int steps;
	private int seconds;
	private boolean stopTimer;
	

	public MyModel()
	{
		lvl=null;
		p=new MySokobanPolicy();
		
	}
	
	
	public MyModel(Level current, SokobanPolicy p) {
		this.lvl=current;
		this.p = p;
		steps= lvl.getSteps();
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
			if(lvl!=null){
				 if(lvl.isFinished()){
					 
					return true;
				 }
				 else return false;
			}
			else return false;
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
			activateTimerAndNotify();
			LinkedList<String> params = new LinkedList<String>();
			params.add("display");
			this.setChanged();
			this.notifyObservers(params);
		}
	}

	@Override
	public void move(String direction) {
		if(p.Possible(lvl, direction)&&!isFinished())
		{
			setSteps(lvl.getSteps()+1);
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
			
			steps = lvl.getSteps();			
		}
		LinkedList<String> params = new LinkedList<String>();
		
		if(isFinished())
		{
			params.add("finished");
			stopTimer();
			setChanged();
			notifyObservers(params);
		}
		else 
		{
			params.add("display");
			setChanged();
			notifyObservers(params);
		}
	}


	
	
	public int getSteps() {
		return steps;
		
	}


	public void setSteps(int steps) {
		this.steps = steps;
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
			params.add("message");
			params.add("saved");
		}
		
	}


	@Override
	public void safeExit() {
		
		if(!isFinished())
		{
			try {
				saveLevel(new FileOutputStream("default.obj"),new MyObjectLevelSaver());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		stopTimer();
		
		
			
		
	}
	private void activateTimerAndNotify()
	{
		
		LinkedList<String> params = new LinkedList<String>();
		
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						while(!stopTimer)
						{
						seconds++;
						Thread.sleep(1000);
						params.add("time");
						setChanged();
						notifyObservers(params);
						System.out.println("fix bind" + seconds);
						
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}).start();
		
	}
	
	private void stopTimer()
	{
		stopTimer = true;
		lvl.setTime(seconds);
		
	}


	@Override
	public int getTime() {
		return seconds;
		
	}


	
	
	
	
	
	

}
