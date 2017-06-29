package controller.commands;

import java.io.IOException;

import model.Model;

/**
 * Updates the time it took to complete the level.
 * @author Or Priesender
 *
 */
public class UpdateTimeCommand extends CommonModelCommand {
	
	public UpdateTimeCommand(Model model){
		this.model = model;
	}
	

	@Override
	public void execute() throws IOException {
		if(params != null){
		model.updateTime(Integer.parseInt(params.removeFirst()));
		}
	}

}
