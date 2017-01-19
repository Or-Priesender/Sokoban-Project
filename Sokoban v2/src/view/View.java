package view;

import java.io.InputStream;
import java.io.OutputStream;

import model.data.Level;
import model.data.LevelObject;

public interface View {
	
	public void display(LevelObject[][] levelData);
	public void start(InputStream in , OutputStream out);
	

}
