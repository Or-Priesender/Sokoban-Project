package levelLoadersAndSavers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import levels.Level;

public class MyXMLLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream in) throws IOException, ClassNotFoundException {
		Level newLevel = new Level();
		XMLDecoder xml = new XMLDecoder(new BufferedInputStream(in));
		newLevel =(Level)xml.readObject();
		
		
		xml.close();
		
		return newLevel;
	}

}
