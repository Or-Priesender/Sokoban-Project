package commands;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import levelLoadersAndSavers.LevelLoader;
import levelLoadersAndSavers.LevelLoaderCreator;
import levelLoadersAndSavers.ObjectLevelLoaderCreator;
import levelLoadersAndSavers.TextLevelLoaderCreator;
import levelLoadersAndSavers.XMLLevelLoaderCreator;
import levels.*;

public class LoadFileCommand implements Command {
	HashMap<String,LevelLoaderCreator> map;
	LevelLoader loader;
	FileInputStream in;
	Level lvl;
	

	
	//loads to the empty level from the given path, must activate getLevel after to get the loaded level!
	public LoadFileCommand(String filename,Level emptyLevel) throws FileNotFoundException 
	{
		map = new HashMap<String,LevelLoaderCreator>();
		in = new FileInputStream(filename);
		
		//configures the needed values to the map
		map.put("obj", new ObjectLevelLoaderCreator());
		map.put("xml", new XMLLevelLoaderCreator());
		map.put("txt",new TextLevelLoaderCreator());
		
		//loader creator  gets his right value according to the file ending
		LevelLoaderCreator c = map.get(filename.substring(filename.length()-3));
		if (c!=null)
			loader = c.create();
		lvl = emptyLevel;
		
	}
	@Override
	public void execute() {
		try{
		lvl = loader.loadLevel(in);
		
		}
		catch(ClassNotFoundException | IOException p)
		{
			p.printStackTrace();
		}
		
	}
	public HashMap<String, LevelLoaderCreator> getMap() {
		return map;
	}
	public void setMap(HashMap<String, LevelLoaderCreator> map) {
		this.map = map;
	}
	public LevelLoader getLoader() {
		return loader;
	}
	public void setLoader(LevelLoader loader) {
		this.loader = loader;
	}
	public FileInputStream getIn() {
		return in;
	}
	public void setIn(FileInputStream in) {
		this.in = in;
	}
	public Level getLvl() {
		return lvl;
	}
	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}

}
