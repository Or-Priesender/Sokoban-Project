package view;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;

import javafx.beans.property.IntegerProperty;
import model.data.level.Level;
import model.data.level.LevelObject;

public interface View {
	
	
	
	public void display(LevelObject[][] levelData);
	
	public void displayFinished();
	
	public void bindSteps(IntegerProperty steps);
	
	public void bindTime(IntegerProperty time);
	
	public void stop();
	

	
	
}
