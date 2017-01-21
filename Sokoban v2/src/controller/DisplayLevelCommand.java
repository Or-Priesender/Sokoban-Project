package controller;

import model.data.Model;
import view.Displayable;
import view.LevelDisplayer;
import view.GUILevelDisplayer;
import view.View;

public class DisplayLevelCommand extends Command {

	
	private Model model;
	private View view;
	
	public DisplayLevelCommand(Model model, View view) {
		
		this.model = model;
		this.view = view;
		
	}
	@Override
	public void execute() {
		
		if(model.getLevelData()!=null)
			view.display(model.getLevelData());
		
		
	}

}
