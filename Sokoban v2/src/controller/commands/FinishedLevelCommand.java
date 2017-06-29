package controller.commands;

import java.io.IOException;

import view.View;

/**
 * This command tells the view that the player completed the stage.
 * @author Or Priesender
 *
 */

public class FinishedLevelCommand extends CommonViewCommand {

	public  FinishedLevelCommand(View view) {
		this.view = view;
	}
	
	@Override
	public void execute() throws IOException {
	
		view.displayFinished();

	}

}
