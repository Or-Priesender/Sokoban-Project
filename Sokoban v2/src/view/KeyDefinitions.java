package view;

import java.beans.XMLDecoder;
import java.io.InputStream;
import java.util.HashMap;

import javafx.scene.input.KeyCode;

public class KeyDefinitions {
	
	private HashMap<KeyCode,KeyCode> definitions;
	
	public KeyDefinitions(InputStream in){
		XMLDecoder decoder = new XMLDecoder(in);
		definitions = new HashMap<KeyCode,KeyCode>();
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
