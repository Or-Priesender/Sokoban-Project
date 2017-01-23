package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import model.data.files.LevelLoader;
import model.data.files.LevelSaver;
import model.data.level.Level;
import model.data.level.LevelObject;
import model.policy.SokobanPolicy;

public interface Model {

	public Level getLevel();
	public boolean isFinished();
	public SokobanPolicy getPolicy();
	public void setPolicy(SokobanPolicy p);
	public void move(String direction);
	public LevelObject[][] getLevelData();
	public void loadLevel(InputStream in,LevelLoader loader) throws ClassNotFoundException, IOException;
	public void saveLevel(OutputStream out,LevelSaver saver) throws IOException;
	
}
