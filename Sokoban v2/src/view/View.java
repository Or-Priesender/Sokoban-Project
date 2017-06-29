package view;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import model.data.level.LevelObject;

/**
 * Facade interface for the view layer, defines all required behavior of the presentation and presentation logic.
 * @author Or Priesender
 *
 */
public interface View {
	
	
	
	public void display(LevelObject[][] levelData);
	
	public void displayFinished();
	
	public void bindSteps(IntegerProperty steps);
	
	public void displayRecord(int record);
	
	public void serverStatus(boolean status);
	
	public void stop();
	
	public void displayAlert(String title,String content);
	
	public void getUserDetails();
	
	public void getCurrentLevelSession();
	
	public void displaySessionsList(List list);
	
	public void displaySolution(String solution);
	
	public void displayHint(String solution);
	

	
	
}
