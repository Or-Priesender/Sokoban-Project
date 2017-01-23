package view;

import model.data.level.Level;
import model.data.level.LevelObject;

public interface Displayable {
	
	public void display();
	public void setLevelData(LevelObject[][] levelData);

}
