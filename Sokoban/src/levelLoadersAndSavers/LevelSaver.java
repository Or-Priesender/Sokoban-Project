package levelLoadersAndSavers;

import java.io.IOException;
import java.io.OutputStream;

import levels.Level;

public interface LevelSaver {
	
	public void saveLevel(Level lvl,OutputStream out) throws IOException;

}
