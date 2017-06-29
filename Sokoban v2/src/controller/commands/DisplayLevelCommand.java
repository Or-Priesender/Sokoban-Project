package controller.commands;

import model.Model;
import view.View;

/**
 * This class uses both Model and View and tells the view to display the level that the model holds.
 * This way the View doesn't need to know the information.
 * @author Or Priesender
 *
 */
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
