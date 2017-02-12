package view;

import java.beans.XMLEncoder;
import java.io.OutputStream;

import javafx.scene.input.KeyCode;

public class KeySetter {
	
	public void setDefaultKeys(OutputStream out){
		XMLEncoder encoder = new XMLEncoder(out);
		
		encoder.writeObject(KeyCode.UP);
		encoder.writeObject(KeyCode.DOWN);
		encoder.writeObject(KeyCode.RIGHT);
		encoder.writeObject(KeyCode.LEFT);
		
		encoder.close();
		
	}
	
	public void setCustomKeys(OutputStream out,KeyCode up,KeyCode down, KeyCode right,KeyCode left){
		XMLEncoder encoder = new XMLEncoder(out);
		
		encoder.writeObject(up);
		encoder.writeObject(down);
		encoder.writeObject(right);
		encoder.writeObject(left);
		
		encoder.close();
		
	}

}
