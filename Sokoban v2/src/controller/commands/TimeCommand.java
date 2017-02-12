package controller.commands;

import java.io.IOException;

import model.Model;
import view.View;

public class TimeCommand extends CommonModelCommand {

	public TimeCommand(Model model)
	{
		this.model = model;
		
	}
	
	@Override
	public void execute() throws IOException {
		
		model.checkRecord(Integer.parseInt(params.removeFirst()));
		

	}

}
