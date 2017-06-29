package view;

import java.beans.XMLEncoder;
import java.io.OutputStream;

import javafx.scene.input.KeyCode;

/**
 * Writes desired key codes to a XML file, later to be interpreted by the KeyDefinitions class. 
 * @author Or Priesender
 *
 */
public class KeySetter {
	
	/**
	 * Sets the default arrow keys to the file.
	 * @param out
	 */
	public void setDefaultKeys(OutputStream out){
		XMLEncoder encoder = new XMLEncoder(out);
		
		encoder.writeObject(KeyCode.UP);
		encoder.writeObject(KeyCode.DOWN);
		encoder.writeObject(KeyCode.RIGHT);
		encoder.writeObject(KeyCode.LEFT);
		
		encoder.close();
		
	}
	
	/**
	 * Sets custom keys to the file.
	 * @param out OutputStream to use writing to the file.
	 * @param up desired key to move up
	 * @param down desired key to move down
	 * @param right desired key to move right
	 * @param left desired key to move left
	 */
	public void setCustomKeys(OutputStream out,KeyCode up,KeyCode down, KeyCode right,KeyCode left){
		XMLEncoder encoder = new XMLEncoder(out);
		
		encoder.writeObject(up);
		encoder.writeObject(down);
		encoder.writeObject(right);
		encoder.writeObject(left);
		
		encoder.close();
		
	}

}
