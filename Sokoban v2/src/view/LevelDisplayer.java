package view;

import model.data.level.LevelObject;

public class LevelDisplayer implements Displayable {

	LevelObject[][] levelData;
	
	LevelDisplayer(LevelObject[][] levelData)
	{
		this.levelData=levelData;
	}
	
	@Override
	public void display() {
		

	}
	@Override
	public void setLevelData(LevelObject[][] levelData) {
		this.levelData = levelData;
		
	}

}
