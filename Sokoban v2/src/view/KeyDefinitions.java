package view;

import java.beans.XMLDecoder;
import java.io.InputStream;
import java.util.HashMap;

import javafx.scene.input.KeyCode;


/**
 * Reads an XML file with 4 key codes (directions) and mapps them to the regular arrow keys.
 * This is used for when you set custom controls with the KeySetter class.
 * @author Or Priesender
 *
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
