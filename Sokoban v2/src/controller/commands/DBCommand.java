package controller.commands;

import java.io.IOException;
import java.util.List;

import model.Model;
import model.data.database.GameSession;
import model.data.level.Level;
import view.View;

public class DBCommand extends CommonModelViewCommand {
	
	
	public DBCommand(Model model,View view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void execute() throws IOException {
		String command = params.removeFirst();
		List<GameSession> results;
		switch(command){
		case "save":
			model.saveSessionToDB(params.removeFirst());
			break;
		case "loadCurrentLevelSession":
			Level current = model.getLevel();
			if(current != null)
				results = model.loadLevelSessionFromDB(model.getLevel().getLevelName());
			else results = model.loadLevelSessionFromDB("%");
			view.displaySessionsList(results);
			break;
		case "loadLevelSession":
			results = model.loadLevelSessionFromDB(params.removeFirst());
			view.displaySessionsList(results);
			break;
		case "loadUserSession":
			results = model.loadUserSessionFromDB(params.removeFirst());
			view.displaySessionsList(results);
			break;
		
		}

	}

}
