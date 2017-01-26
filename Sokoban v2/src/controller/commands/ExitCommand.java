package controller.commands;

import controller.Controller;
import model.Model;
import view.View;

public class ExitCommand extends CommonModelViewCommand {
	
	Controller controller;
	
	public ExitCommand(Model model,View view,Controller controller) {
		this.model = model;
		this.view = view;
		this.controller = controller;
	}
	@Override
	public void execute() {
		
		model.safeExit();
		controller.stop();
		view.stop();
		

	}

}
