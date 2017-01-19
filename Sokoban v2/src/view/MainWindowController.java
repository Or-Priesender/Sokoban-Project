package view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController implements Initializable{
	
	@FXML
	LevelDisplayerGUI levelDisplayer;
			 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		levelDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->levelDisplayer.requestFocus());
		levelDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>(){
			
			@Override
			public void handle(KeyEvent event) {
				int r = levelDisplayer.getcRow();
				int c = levelDisplayer.getcCol();
				
				if(event.getCode()== KeyCode.UP)
				{
					
					levelDisplayer.setCharacterPosition(r-1, c);
				}
				if(event.getCode()== KeyCode.DOWN)
				{
					levelDisplayer.setCharacterPosition(r+1, c);
				}
				if(event.getCode()== KeyCode.RIGHT)
				{
					levelDisplayer.setCharacterPosition(r, c+1);
				}
				if(event.getCode()== KeyCode.LEFT)
				{
					levelDisplayer.setCharacterPosition(r, c-1);
				}
				
			}
		});
	}
	public void start()
	{
		System.out.println("start");
	}
	
	public void openFile(){
		
		FileChooser fc = new FileChooser();
			fc.setTitle("Open level file");
			fc.setInitialDirectory(new File("./resources"));
			fc.setSelectedExtensionFilter(new ExtensionFilter("xml files (*.xml)", "*.xml"));
			File chosen = fc.showOpenDialog(null);
			if(chosen!=null)
			{
				System.out.println(chosen.getName());
				
			}
	}

	
	
}
