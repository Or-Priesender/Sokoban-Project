package controller.commands;

import java.io.IOException;
import java.util.List;

import model.Model;
import model.data.database.GameSession;
import view.View;

public class LoadSessionFromDBCommand extends CommonModelViewCommand {

	public  LoadSessionFromDBCommand(Model model,View view) {
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void execute() throws IOException {
		
			List<GameSession> list = model.loadLevelSessionFromDB(model.getLevel().getLevelName());
			if(list != null)
				view.displaySessionsList(list);

	}

}
