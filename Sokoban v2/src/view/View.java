package view;

import javafx.beans.property.IntegerProperty;
import model.data.level.LevelObject;

public interface View {
	
	
	
	public void display(LevelObject[][] levelData);
	
	public void displayFinished();
	
	public void bindSteps(IntegerProperty steps);
	
	public void displayRecord(int record);
	
	public void stop();
	

	
	
}
