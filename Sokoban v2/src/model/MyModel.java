package model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import model.data.database.DataBaseManager;
import model.data.database.ServerDBManager;
import model.data.database.User;
import model.data.files.LevelLoader;
import model.data.files.LevelSaver;
import model.data.files.MyObjectLevelSaver;
import model.data.level.Level;
import model.data.level.LevelObject;
import model.data.level.Player;
import model.data.level.Point;
import model.policy.MySokobanPolicy;
import model.policy.SokobanPolicy;

/*
 * This class groups the whole Model layer into one usable class.
 */

public class MyModel extends Observable implements Model {

	protected Level lvl;
	protected SokobanPolicy p;
	protected int steps;
	protected int seconds;
	DataBaseManager dbm;
	Socket socket;
	String solutionToCurrent;

	public MyModel()
	{

		try {
			socket = new Socket("127.0.0.1",11934);
			lvl=null;
			p=new MySokobanPolicy();
			dbm = new ServerDBManager(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public MyModel(Level current, SokobanPolicy p,DataBaseManager dbm) {
		this.lvl=current;
		this.p = p;
		steps= lvl.getSteps();
		this.dbm = dbm;
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
	public List loadLevelSessionFromDB(String levelName) {
		return dbm.getGameSessionTableForLevel(levelName);

	}
	@Override
	public List loadUserSessionFromDB(String username){

		return dbm.getGameSessionTableForUser(username);
	}

	//checks if the player finished the stage
	@Override
	public boolean isFinished() {
		if(lvl==null)
			return false;
		else return lvl.isFinished();
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

	//loads a level and sends a display message to let the controller know that the level has changed
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

	//moves the player to desired direction if the implemented policy allows it
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
		//checks if the player has finished the stage after moving to desired location
		if(isFinished())
		{
			params.add("finished");
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
			params.add("Saved");
			params.add("Level saved succesfully");
			notifyObservers(params);
		}

	}

	//saves before exit
	@Override
	public void safeExit() {
		if(!isFinished())
		{
			try {
				saveLevel(new FileOutputStream("default.obj"),new MyObjectLevelSaver());

			} catch (IOException e) {

				e.printStackTrace();
			}
			finally{
				if(dbm != null)
					dbm.closeDB();
			}
		}

	}

	@Override
	public int getTime() {
		return seconds;

	}

	@Override
	public void checkRecord(int num) {
		if(lvl != null)
		{
			if(num < lvl.getTime())
				lvl.setTime(num);
		}
	}

	@Override
	public void saveSessionToDB(String username) {

		User user = new User(username);
		if(lvl != null){
			dbm.saveUserAndLevel(user, lvl);
		}

	}

	@Override
	public void updateTime(int seconds) {
		if(lvl!=null)
			lvl.setTime(seconds);

	}

	@Override
	public String requestSolution() {
		LinkedList<String> params = new LinkedList<>();
		String solution = dbm.getSolution(this.lvl);
		return solution;

	}
}
