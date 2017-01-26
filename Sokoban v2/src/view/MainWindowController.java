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

import javax.management.timer.Timer;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.data.level.Level;
import model.data.level.LevelObject;


public class MainWindowController extends Observable implements View,Initializable{
	
	@FXML
	GUILevelDisplayer levelDisplayer;
	
	@FXML
	Label stepsLabel,timeLabel;
	
	
	
	
	boolean starting = true;
	
			 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(starting){
			levelDisplayer.displayOpenPage();
			starting = false;
		}
		
		levelDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->levelDisplayer.requestFocus());
		levelDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>(){
			
			@Override
			public void handle(KeyEvent event) {
				
				List<String> params=new LinkedList<String>();
				
				
				if(event.getCode()== KeyCode.UP)
				{
					
					levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerUpFileName());
					params.add("move");
					params.add("up");
					setChanged();
					notifyObservers(params);
					
				}
				if(event.getCode()== KeyCode.DOWN)
				{
					levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerDownFileName());
					params.add("move");
					params.add("down");
					setChanged();
					notifyObservers(params);
				}
				if(event.getCode()== KeyCode.RIGHT)
				{
					levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerRightFileName());
					params.add("move");
					params.add("right");
					setChanged();
					notifyObservers(params);
				}
				if(event.getCode()== KeyCode.LEFT)
				{
					levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerLeftFileName());
					params.add("move");
					params.add("left");
					setChanged();
					notifyObservers(params);
					
				}
				
					event.consume();
				
			}
		});
	}
	public void bindTime(IntegerProperty time)
	{
		timeLabel.textProperty().bind(time.asString());
	}
	
	public void bindSteps(IntegerProperty steps)
	{
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				stepsLabel.textProperty().bind(steps.asString());
				
			}
		});
	}
	
	public void Exit()
	{
		LinkedList<String> params = new LinkedList<String>();
		params.add("exit");
		setChanged();
		notifyObservers(params);
	}
	
	public void openFile(){
		
		FileChooser fc = new FileChooser();
			fc.setTitle("Open level file");
			fc.setInitialDirectory(new File("./resources/"));
			fc.getExtensionFilters().addAll(
					new ExtensionFilter("Text","*.txt"),
					new ExtensionFilter("XML","*.xml"),
					new ExtensionFilter("Object","*.obj")
					);
			File chosen = fc.showOpenDialog(null);
			if(chosen!=null)
			{
				LinkedList<String> params = new LinkedList<String>();
				params.add("load");
				params.add(chosen.getPath());
				setChanged();
				notifyObservers(params);
				
			}
	}
	
	public void saveFile(){
		FileChooser fc = new FileChooser();
		fc.setTitle("save level");
		fc.setInitialDirectory(new File("./resources"));
		fc.getExtensionFilters().addAll(
				
				new ExtensionFilter("Text","*.txt"),
				new ExtensionFilter("XML","*.xml"),
				new ExtensionFilter("Object","*.obj")
				
				);
		
		fc.setInitialFileName("level.xml");
		File saved = fc.showSaveDialog(null);
		if(saved!=null)
		{
			LinkedList<String> params = new LinkedList<String>();
			params.add("save");
			params.add(saved.getPath());
			setChanged();
			notifyObservers(params);
		}
		else
		{
			//display error
		}
		
	}
	
	public void safeExit()
	{
		LinkedList<String> params = new LinkedList<String>();
		params.add("exit");
		setChanged();
		notifyObservers(params);
	}
	
	@Override
	public void display(LevelObject[][] levelData) {
		if(levelData!=null){
		levelDisplayer.display(levelData);
		}
	}
	
	@Override
	public void displayFinished()
	{
		levelDisplayer.display(levelDisplayer.getLevelData());
		levelDisplayer.displayFinished();
		
	}
	
	@Override
	public void stop()
	{
		Platform.exit();
	}

	
	
	
	
	
}
