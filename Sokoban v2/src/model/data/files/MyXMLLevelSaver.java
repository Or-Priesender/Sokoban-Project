package model.data.files;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

import model.data.level.Level;



/*
 * Saves an xml file with any InputStream
 */

public class MyXMLLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(Level lvl, OutputStream out) {
		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(out));
		
		e.writeObject(lvl);
		e.close();
		

	}

}
