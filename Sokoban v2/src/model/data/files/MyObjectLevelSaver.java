package model.data.files;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import model.data.level.Level;

public class MyObjectLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(Level lvl, OutputStream out) throws IOException {
		ObjectOutputStream o = new ObjectOutputStream(out);
		o.writeObject(lvl);
		o.close();

	}

}
