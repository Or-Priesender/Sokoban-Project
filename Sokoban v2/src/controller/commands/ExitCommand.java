package controller.commands;

import controller.Controller;
import controller.SokobanController;
import controller.server.MyServer;
import javafx.application.Platform;
import model.Model;
import view.View;


public class ExitCommand extends CommonModelViewCommand {
	
	SokobanController controller;
	
	
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
