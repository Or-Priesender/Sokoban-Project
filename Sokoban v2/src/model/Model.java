package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import model.data.database.User;
import model.data.files.LevelLoader;
import model.data.files.LevelSaver;
import model.data.level.Level;
import model.data.level.LevelObject;
import model.policy.SokobanPolicy;

/**
 * The Model facade interface, defines the model's required behavior.
 * @author Or Priesender
 *
 */
public interface Model {

	public Level getLevel();
	public boolean isFinished();
	public SokobanPolicy getPolicy();
	public void setPolicy(SokobanPolicy p);
	public void move(String direction);
	public LevelObject[][] getLevelData();
	public void loadLevel(InputStream in,LevelLoader loader) throws ClassNotFoundException, IOException;
	public void saveLevel(OutputStream out,LevelSaver saver) throws IOException;
	public void safeExit();
	public void checkRecord(int num);
	public int getSteps();
	public int getTime();
	public void updateTime(int seconds);
	public void saveSessionToDB(String username);
	public List loadLevelSessionFromDB(String levelName);
	public List loadUserSessionFromDB(String username);
	public String requestSolution();
	
	
}
