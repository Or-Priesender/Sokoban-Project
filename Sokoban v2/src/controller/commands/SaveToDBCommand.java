package controller.commands;

import java.io.IOException;

import model.Model;
import view.View;

public class SaveToDBCommand extends CommonModelViewCommand {
	
	public SaveToDBCommand(Model model,View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void execute() throws IOException {

		model.saveSessionToDB(params.removeFirst());
		

	}

}
