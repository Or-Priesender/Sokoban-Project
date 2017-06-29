package model.data.files;

import java.io.IOException;
import java.io.OutputStream;

import model.data.level.Level;

/**
 * Defines a level saver behavior.
 * @author Or Priesender
 *
 */
public interface LevelSaver {
	
	public void saveLevel(Level lvl,OutputStream out) throws IOException;

}
