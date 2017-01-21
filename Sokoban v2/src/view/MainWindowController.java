package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.data.Level;
import model.data.LevelObject;


public class MainWindowController extends Observable implements Initializable,View{
	
	@FXML
	GUILevelDisplayer levelDisplayer;
	
			 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		levelDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->levelDisplayer.requestFocus());
		levelDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>(){
			
			@Override
			public void handle(KeyEvent event) {
				
				
				int r = levelDisplayer.getcRow();
				int c = levelDisplayer.getcCol();
				
				List<String> params=new LinkedList<String>();
				
				System.out.println("1");
				if(event.getCode()== KeyCode.UP)
				{
					System.out.println("dsadas");
					params.add("move");
					params.add("up");
					setChanged();
					notifyObservers(params);
					
					
				}
				if(event.getCode()== KeyCode.DOWN)
				{
					params.add("move");
					params.add("down");
					setChanged();
					notifyObservers(params);
				}
				if(event.getCode()== KeyCode.RIGHT)
				{
					params.add("move");
					params.add("right");
					setChanged();
					notifyObservers(params);
				}
				if(event.getCode()== KeyCode.LEFT)
				{
					params.add("move");
					params.add("left");
					setChanged();
					notifyObservers(params);
					
				}
				
					
				
			}
		});
	}
	public void start()
	{
		
	}
	
	public void openFile(){
		
		FileChooser fc = new FileChooser();
			fc.setTitle("Open level file");
			fc.setInitialDirectory(new File("./resources/"));
			fc.setSelectedExtensionFilter(new ExtensionFilter("Text Files (*.txt)", ("*.txt")));
			File chosen = fc.showOpenDialog(null);
			if(chosen!=null)
			{
				LinkedList<String> params = new LinkedList<String>();
				params.add("load");
				params.add("level1.txt");
				for(String s : params)
					System.out.println(s);
				
				
				setChanged();
				notifyObservers(params);
				
			}
	}
	
	@Override
	public void display(LevelObject[][] levelData) {
		if(levelData!=null){
		levelDisplayer.display(levelData);
		}
	}
	@Override
	public void startCustomIO(InputStream in, OutputStream out) {
		// TODO currently has no body
		
	}
	
	
	
	
}
