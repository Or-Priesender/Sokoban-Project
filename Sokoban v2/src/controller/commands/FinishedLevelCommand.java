package controller.commands;

import java.io.IOException;

import view.View;

public class FinishedLevelCommand extends CommonViewCommand {

	public  FinishedLevelCommand(View view) {
		this.view = view;
	}
	
	@Override
	public void execute() throws IOException {
		
		view.displayFinished();

	}

}
