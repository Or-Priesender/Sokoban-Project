package controller.commands;


import controller.SokobanController;
import model.Model;
import view.View;

/**
 * 
 * This command performs a safe exit from all the layers (Model,View,Controller).
 *
 * @author Or Priesender
 *
 */

public class ExitCommand extends CommonModelViewCommand {
	
	protected SokobanController controller;
	
	
	public ExitCommand(Model model,View view,SokobanController controller) {
		
		this.model = model;
		this.view = view;
		this.controller = controller;
	}
	@Override
	public void execute() {
		
		model.safeExit();
		controller.safeExit();
		view.stop();
		
	}

}
