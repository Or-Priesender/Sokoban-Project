package view;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import model.data.level.LevelObject;


public class MainWindowController extends Observable implements View,Initializable{
	
	@FXML
	GUILevelDisplayer levelDisplayer;
	
	@FXML
	Label stepsLabel,timeLabel;
	
	private IntegerProperty seconds;
	private Timeline timeline;
	private boolean firstLaunch = true;
	private boolean gameInProgress = false;
	private boolean firstMove = true;
	private KeyDefinitions currentDef;
	
			 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(firstLaunch){
			seconds = new SimpleIntegerProperty(0);
			timeLabel.textProperty().bind(seconds.asString());
			levelDisplayer.displayOpenPage();
			
			timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.setRate(1.0);
			timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1000000),new KeyValue(seconds,1000000)));
			
			KeySetter k = new KeySetter();
			try {
				k.setDefaultKeys(new FileOutputStream("resources/settings/defaultKeys.xml"));
				currentDef = new KeyDefinitions(new FileInputStream("resources/settings/defaultKeys.xml"));
			} catch (FileNotFoundException e1) {
				errorAlert("Default Keys Error", "Problem setting default keys");
			}
			
			
			firstLaunch = false;
			
		}
		
		levelDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->levelDisplayer.requestFocus());
		levelDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>(){
			
			@Override
			public void handle(KeyEvent event) {
				
				List<String> params=new LinkedList<String>();
				
			if(gameInProgress){	
				if(currentDef.getCommand(event.getCode())==KeyCode.UP)
				{
					if(firstMove){
						timeline.playFromStart();
						firstMove = false;
					}
					levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerUpFileName());
					params.add("move");
					params.add("up");
					setChanged();
					notifyObservers(params);
					
				}
				else if(currentDef.getCommand(event.getCode())==KeyCode.DOWN)
				{
					if(firstMove){
						timeline.playFromStart();
						firstMove = false;
					}
					
					levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerDownFileName());
					params.add("move");
					params.add("down");
					setChanged();
					notifyObservers(params);
				}
				else if(currentDef.getCommand(event.getCode())==KeyCode.RIGHT)
				{
					if(firstMove){
						timeline.playFromStart();
						firstMove = false;
					}
					
					levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerRightFileName());
					params.add("move");
					params.add("right");
					setChanged();
					notifyObservers(params);
				}
				else if(currentDef.getCommand(event.getCode())==KeyCode.LEFT)
				{
					if(firstMove){
						timeline.playFromStart();
						firstMove = false;
					}
					
					levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerLeftFileName());
					params.add("move");
					params.add("left");
					setChanged();
					notifyObservers(params);
					
				}
				
					event.consume();
				
			}
			}
		});
	}
	
	public void changeControls()
	{
				
				List<String> layouts = new ArrayList<>();
				KeySetter keySetter = new KeySetter();
				layouts.add("WSAD");
				layouts.add("Default - Arrow Keys");
				layouts.add("Custom..");
				
				ChoiceDialog<String> mainDialog = new ChoiceDialog<>("Default - Arrow Keys",layouts);
				mainDialog.setTitle("changing settings");
				mainDialog.setHeaderText("Choose control layout");
				
				Optional<String> firstChoice = mainDialog.showAndWait();
				if(firstChoice.isPresent()){
					try{
					if(firstChoice.get().compareTo("WSAD")==0)
					{
							keySetter.setCustomKeys(new FileOutputStream("resources/settings/WSAD.xml"), KeyCode.valueOf("W"),KeyCode.valueOf("S"),KeyCode.valueOf("D"),KeyCode.valueOf("A"));
							currentDef = new KeyDefinitions(new FileInputStream("resources/settings/WSAD.xml"));	
					}
					
					else if(firstChoice.get().compareTo("Default - Arrow Keys")==0)
							currentDef = new KeyDefinitions(new FileInputStream("resources/settings/defaultKeys.xml"));
							
					else if(firstChoice.get().compareTo("Custom..")==0)
						customControlsDialog();
					
					}
					catch(FileNotFoundException e) { errorAlert("File Not Found","Returning to default settings");}
					
				}
				
	
	}
	
	private void customControlsDialog() throws FileNotFoundException{
		
		KeyCode up=null,down=null,right=null,left=null;
		KeySetter keySetter = new KeySetter();
		List<String> choices = new ArrayList<>();
		addControlsToList(choices);
		
		ChoiceDialog<String> customDialog = new ChoiceDialog<>("UP",choices);
		customDialog.setTitle("changing settings");
		customDialog.setHeaderText("Custom settings");
		
		customDialog.setContentText("Choose Up Key");
		Optional<String> theChoice = customDialog.showAndWait();
		if(theChoice.isPresent())
			up = KeyCode.valueOf(theChoice.get());
		
		
		customDialog.setContentText("Choose Down Key");
		 theChoice = customDialog.showAndWait();
		if(theChoice.isPresent())
			down = KeyCode.valueOf(theChoice.get());
		
		
		customDialog.setContentText("Choose Right Key");
		theChoice = customDialog.showAndWait();
		if(theChoice.isPresent())
			right = KeyCode.valueOf(theChoice.get());
		
		
		customDialog.setContentText("Choose Left Key");
		theChoice = customDialog.showAndWait();
		if(theChoice.isPresent())
			left = KeyCode.valueOf(theChoice.get());
		
		if(up != null && down != null && right != null && left != null){
			
		try{
		keySetter.setCustomKeys(new FileOutputStream("resources/settings/custom.xml"), up,down,right,left);
		currentDef = new KeyDefinitions(new FileInputStream("resources/settings/custom.xml"));
		}
		catch(FileNotFoundException e) {errorAlert("file not found", "returning to default keys") ;
		}
		
		}
		
		else {
			errorAlert("setting controls failure","returning to default keys");
			currentDef = new KeyDefinitions(new FileInputStream("resources/settings/defaultKeys.xml"));
		}

	}
	
	private void addControlsToList(List<String> list){
		
		String abc[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String arrows[]={"UP","DOWN","RIGHT","LEFT"};
		String numpad[]={"NUMPAD0","NUMPAD1","NUMPAD2","NUMPAD3","NUMPAD4","NUMPAD5","NUMPAD6","NUMPAD7","NUMPAD8","NUMPAD9"};
		list.addAll(Arrays.asList(abc));
		list.addAll(Arrays.asList(arrows));
		list.addAll(Arrays.asList(numpad));
		
		
		
		
	}
	
	private void checkRecord(int time){
		LinkedList<String> params = new LinkedList<String>();
		params.add("time");
		params.add(String.valueOf(time));
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
	
	public void exit()
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
				
				if(timeline!=null){
					checkRecord(seconds.intValue());
					timeline.stop();
					seconds.set(0);
					
					gameInProgress = true;
					firstMove = true;
					
					
				
				}
				else
				{
					errorAlert("Error loading file","could not load file, please try again.");
				}
				
				
				
				
				
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
			errorAlert("Error saving file","could not save file, please try again.");
		}
		
	}
	
	private void errorAlert(String title,String content){
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText("Failure");
		alert.setContentText(content);
		alert.showAndWait();
		
	}
	
	public void openJavaDoc(){
		
		try {
			Desktop.getDesktop().browse(new URI("www.google.com"));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void safeExit()
	{
		if(timeline != null)
		{
			checkRecord(seconds.intValue());
			timeline.stop();
			
		}
		LinkedList<String> params = new LinkedList<String>();
		params.add("exit");
		setChanged();
		notifyObservers(params);
	}
	
	@Override
	public void display(LevelObject[][] levelData) {
		if(levelData!=null){
		try { levelDisplayer.display(levelData); } catch (FileNotFoundException e) {
			
			errorAlert("File not found exception", "in MainWindowController.display");
		}
		
		}
	}
	
	@Override
	public void displayFinished()
	{
		if(timeline != null){
			checkRecord(seconds.intValue());
			timeline.stop();
		}
		try{
		gameInProgress = false;
		levelDisplayer.display(levelDisplayer.getLevelData());
		levelDisplayer.displayFinished();
		} catch(FileNotFoundException e) { errorAlert("File not found exception", "in MainWindowController.displayFinished");} 
		
	}
	
	@Override
	public void stop()
	{
		Platform.exit();
	}

	@Override
	public void displayRecord(int record) {
		
		//TODO display record ! 
		
	}

	
	
	
	
	
}
