package controller.commands;

import model.Model;
import view.Displayable;
import view.LevelDisplayer;
import view.GUILevelDisplayer;
import view.View;

public class DisplayLevelCommand extends CommonModelViewCommand {

	
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
