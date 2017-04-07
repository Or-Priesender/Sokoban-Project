package controller.commands;

import java.io.IOException;

import model.Model;

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
