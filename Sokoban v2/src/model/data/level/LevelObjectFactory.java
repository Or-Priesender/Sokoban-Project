package model.data.level;

import java.util.HashMap;

import model.data.files.Createable;

public class LevelObjectFactory {
	
	HashMap<Character, Createable> hm;
	
	public LevelObjectFactory()
	{
		
		hm = new HashMap<Character,Createable>();
		hm.put('#', new WallCreator());
		hm.put('@', new BoxCreator());
		hm.put('A', new PlayerCreator());
		hm.put('o', new DestinationCreator());
		hm.put(' ', null);
	}
	
	public LevelObject getObject(char c, Point pos)
	{
		Createable obj = hm.get(c);
		if(obj!=null)
			return obj.create(pos);
		else return null;
		
	}

}
