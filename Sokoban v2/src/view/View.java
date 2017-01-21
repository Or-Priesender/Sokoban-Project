package view;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;

import model.data.Level;
import model.data.LevelObject;

public interface View {
	
	
	
	public void display(LevelObject[][] levelData);
	
	public void startCustomIO(InputStream in , OutputStream out);

	public void start();

}
