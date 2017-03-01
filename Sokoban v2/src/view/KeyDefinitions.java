package view;

import java.beans.XMLDecoder;
import java.io.InputStream;
import java.util.HashMap;

import javafx.scene.input.KeyCode;

/*
 * This class converts any key from an XML file to the arrow keys.
 */

public class KeyDefinitions {
	
	private HashMap<KeyCode,KeyCode> definitions;
	
	public KeyDefinitions(InputStream in){
		XMLDecoder decoder = new XMLDecoder(in);
		definitions = new HashMap<KeyCode,KeyCode>();
		
		//first key code in the XML file will be the up arrow and so on
		
		definitions.put((KeyCode)decoder.readObject(), KeyCode.UP);
		definitions.put((KeyCode)decoder.readObject(), KeyCode.DOWN);
		definitions.put((KeyCode)decoder.readObject(), KeyCode.RIGHT);
		definitions.put((KeyCode)decoder.readObject(), KeyCode.LEFT);
		
		decoder.close();
		
	}
	
	public KeyCode getCommand(KeyCode key){
		return definitions.get(key);
	}

}
