package model.data.files;

import java.io.IOException;
import java.io.OutputStream;

import model.data.level.Level;

public interface LevelSaver {
	
	public void saveLevel(Level lvl,OutputStream out) throws IOException;

}
