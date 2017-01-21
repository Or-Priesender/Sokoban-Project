package controller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import model.data.Level;
import model.data.LevelSaver;
import model.data.LevelSaverCreator;
import model.data.Model;
import model.data.ObjectLevelSaverCreator;
import model.data.TextLevelSaverCreator;
import model.data.XMLLevelSaverCreator;

public class SaveFileCommand extends Command {

	HashMap<String,LevelSaverCreator> map;
	FileOutputStream out;
	LevelSaver saver;
	
	Model model;
	
	public SaveFileCommand(Model model) throws FileNotFoundException
	{
		this.model = model;
		map = new HashMap<String,LevelSaverCreator>();
		
		//configuration of the map, can add more in the future
		map.put("obj", new ObjectLevelSaverCreator());
		map.put("xml", new XMLLevelSaverCreator());
		map.put("txt", new TextLevelSaverCreator());
		
		
		
		
	}
	
	@Override
	public void execute() throws IOException {
		
		String filename = params.removeFirst();
		out = new FileOutputStream(filename);
		LevelSaverCreator c = map.get(filename.substring(filename.length()-3));
		
		if(c!=null){
			saver = c.create();
			if(saver!=null)
				model.saveLevel(out, saver);
		}
	}

}
