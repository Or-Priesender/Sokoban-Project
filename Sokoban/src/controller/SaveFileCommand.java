package controller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import model.data.Level;
import model.data.LevelSaver;
import model.data.LevelSaverCreator;
import model.data.ObjectLevelSaverCreator;
import model.data.TextLevelSaverCreator;
import model.data.XMLLevelSaverCreator;

public class SaveFileCommand implements Command {

	HashMap<String,LevelSaverCreator> map;
	FileOutputStream out;
	LevelSaver saver;
	Level toSave;
	
	public SaveFileCommand(String filename,Level toSave) throws FileNotFoundException
	{
		this.toSave=toSave;
		out = new FileOutputStream(filename);
		map = new HashMap<String,LevelSaverCreator>();
		
		//configuration of the map, can add more in the future
		map.put("obj", new ObjectLevelSaverCreator());
		map.put("xml", new XMLLevelSaverCreator());
		map.put("txt", new TextLevelSaverCreator());
		
		LevelSaverCreator c = map.get(filename.substring(filename.length()-3));
		if(c!=null)
			saver = c.create();
		
		
	}
	
	@Override
	public void execute() throws IOException {
		saver.saveLevel(toSave, out);
		

	}

}
