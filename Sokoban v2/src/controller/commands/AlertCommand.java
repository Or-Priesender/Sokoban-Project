package controller.commands;

import java.io.IOException;

import javafx.application.Platform;
import view.View;

public class AlertCommand extends CommonViewCommand {

	public AlertCommand(View view) {
		this.view = view;
		
		
	}
	
	@Override
	public void execute() throws IOException {
		
		String title = params.removeFirst();
		String content = params.removeFirst();
		if(title!=null)
		{	
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					view.displayAlert(title, content);					
				}
			});
		}

	}

}
