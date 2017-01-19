package view;

import model.data.Level;

public class LevelDisplayer implements Displayable {

	Level myLvl;
	Displayable disp;
	LevelDisplayer(Level lvl)
	{
		disp = null;
		this.myLvl=lvl;
	}
	public LevelDisplayer(Level lvl, Displayable d)
	{
		this.myLvl=lvl;
		this.disp=d;
	}
	@Override
	public void display() {
		

	}
	@Override
	public void setLevel(Level lvl) {
		this.myLvl = lvl;
		
	}

}
