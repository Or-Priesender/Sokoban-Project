package view;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.data.database.GameSession;
import model.data.level.LevelObject;
import shared.SolutionDecoder;

/*
 * This class is the GUI's controller, it gets all data from an XML file outside the code.
 */

public class MainWindowController extends Observable implements View,Initializable,Observer{
	
	ScoreBoardController scoreBoard;
	
	@FXML
	GUILevelDisplayer levelDisplayer;
	
	@FXML
	Label stepsLabel,timeLabel,serverLabel;
	
	private IntegerProperty seconds;
	private Timeline timeline;
	
	private boolean firstLaunch = true;
	private boolean gameInProgress = false;
	private boolean firstMove = true;
	private KeyDefinitions currentDef;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//set up variables for when the program is starting
		if(firstLaunch){
			serverStatus(false);
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
				
			if(gameInProgress){	
				//checks according to whatever controls you set up
				if(currentDef.getCommand(event.getCode())==KeyCode.UP)
				{
					move("up");
				}
				else if(currentDef.getCommand(event.getCode())==KeyCode.DOWN)
				{
					move("down");
				}
				else if(currentDef.getCommand(event.getCode())==KeyCode.RIGHT)
				{
					move("right");
				}
				else if(currentDef.getCommand(event.getCode())==KeyCode.LEFT)
				{
					move("left");
				}
				
					event.consume();
				
			}
			}

			
		});
	}
	
	private void move(String direction) {
		LinkedList<String> params = new LinkedList<>();
		if(firstMove){
			timeline.playFromStart();
			firstMove = false;
		}
		switch(direction){
		case "up":
			levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerUpFileName());
			break;
		case "down":
			levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerDownFileName());
			break;
		case "left":
			levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerLeftFileName());
			break;
		case "right":
			levelDisplayer.setPlayerFileName(levelDisplayer.getPlayerRightFileName());
			break;
			default:break;
			
		}
		params.add("move");
		params.add(direction);
		setChanged();
		notifyObservers(params);
		
	}
	
	public void requestSolution(){
		
		if(levelDisplayer.getLevelData() == null)
			return;
		
		List<String> params = new LinkedList<>();
		params.add("getSolution");
		params.add("solution");
		setChanged();
		notifyObservers(params);
	}
	
	public void requestHint(){
		
		if(levelDisplayer.getLevelData()==null)
			return;
		
		List<String> params = new LinkedList<>();
		params.add("getSolution");
		params.add("hint");
		setChanged();
		notifyObservers(params);
	}
	
	//change controls to already prepared layouts or a custom layout.
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
	
	//displays an alert to the string
	@Override
	public void displayAlert(String title,String content) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText("");
		if(content != null)
			alert.setContentText(content);
		else alert.setContentText(title);
		alert.showAndWait();
		
		
	}
	
	//should check if user chose same controls for two actions
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
	//TODO : ADD TIME COMMAND TO UPDATE THE RECORD IN THE MODEL
	private void checkRecord(int time){
		LinkedList<String> params = new LinkedList<String>();
		params.add("time");
		params.add(String.valueOf(time));
	}
	
	//binds between the steps label and the steps given by the controller
	public void bindSteps(IntegerProperty steps)
	{
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				stepsLabel.textProperty().bind(steps.asString());
				
			}
		});
	}
	
	//user informs the program he wants to exit.
	public void exit()
	{
		LinkedList<String> params = new LinkedList<String>();
		params.add("exit");
		setChanged();
		notifyObservers(params);
	}
	
	//load a level
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
	
	//save a level
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
	//displays an error alert
	private void errorAlert(String title,String content){
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText("Failure");
		alert.setContentText(content);
		alert.showAndWait();
		
	}
	
	//redirects to the program's java doc - currently N/A test only
	public void openJavaDoc(){
		
		try {
			Desktop.getDesktop().browse(new URI("www.google.com"));
		} catch (IOException | URISyntaxException e) {
			
			e.printStackTrace();
		}
	}
	
	//saves needed values before exit
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
	
	//invoke GUI level displayer
	@Override
	public void display(LevelObject[][] levelData) {
		if(levelData!=null){
		try { levelDisplayer.display(levelData); } catch (FileNotFoundException e) {
			
			errorAlert("File not found exception", "in MainWindowController.display");
		}
		
		}
	}
	
	//invoke GUI to display finish screen
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
		String steps = stepsLabel.getText();
		String secs = timeLabel.getText();
		//updates the level with the current seconds
		updateTime();
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				getUserDetails();
				
			}
		});
		
		
		} catch(FileNotFoundException e) { errorAlert("File not found exception", "in MainWindowController.displayFinished");} 
		
	}
	
	@Override
	public void getCurrentLevelSession(){
		LinkedList<String> params = new LinkedList<String>();
		
		params.add("DB");
		params.add("loadCurrentLevelSession");
		setChanged();
		notifyObservers(params);
	}
	
	@Override
	public void displaySessionsList(List list) { 
			if(list == null){
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						displayAlert("Error", "No Data To Show");
						
					}
				});
			
			return;
			}		
		
		try{
			if(scoreBoard!=null){
				scoreBoard.setTable(list); return;
				}
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("boot/ScoreBoard.fxml"));
			Scene scene = new Scene(loader.load(),800,400);
			scoreBoard = loader.getController();
			scoreBoard.setTable(list);
			scoreBoard.addObserver(this);
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					Stage stage = new Stage();
					stage.setTitle("Score Board");
					stage.setScene(scene);
					stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						public void handle(WindowEvent event) {
							if(event.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST)
								scoreBoard=null;
						};
					});
					stage.show();
				}
			});
			} catch(IOException e){
				e.printStackTrace();
			}
	
	}
	private void updateTime(){
		LinkedList<String> params = new LinkedList<String>();
		params.add("time");
		params.add(timeLabel.getText());
		setChanged();
		notifyObservers(params);
	}
	@Override
	public void getUserDetails(){
			
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Please insert username");
		dialog.setTitle("Submit score");
		dialog.setHeaderText("Join the scoreboard");
		
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()){
			
			LinkedList<String> params = new LinkedList<String>();
			params.add("DB");
			params.add("save");
			params.add(result.get());
			//params.add(timeLabel.getText());//used?
			//params.add(stepsLabel.getText());//used?
			setChanged();
			notifyObservers(params);
		}
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

	//set the label to current status
	@Override
	public void serverStatus(boolean status) {
		if(status)
			serverLabel.setText("On");
		else
			serverLabel.setText("Off");
	}
	//The main controller allows other windows to update the project's controller
	@Override
	public void update(Observable arg0, Object arg1) {
		LinkedList<String> params = (LinkedList<String>) arg1;
		setChanged();
		notifyObservers(params);
	}

	@Override
	public void displaySolution(String solution) {
		List<String> actions = SolutionDecoder.decompress(solution);
		new Thread(()->{
			for(String s : actions){
				if(s.endsWith("Up")){
					move("up");
				}
				else if(s.endsWith("Down")){
					move("down");
				}
				else if(s.endsWith("Left")){
					move("left");
				}
				else if(s.endsWith("Right")){
					move("right");
				}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}).start();
		
	}

	@Override
	public void displayHint(String solution) {
		List<String> actions = SolutionDecoder.decompress(solution);
		for(String firstAction : actions){
			
				if(firstAction.endsWith("Up")){
					Platform.runLater(()->displayAlert("HINT", "Move Up !"));
					break;
				}
				if(firstAction.endsWith("Down")){
					Platform.runLater(()->displayAlert("HINT", "Move Down !"));
					break;
				}
				if(firstAction.endsWith("Left")){
					Platform.runLater(()->displayAlert("HINT", "Move Left !"));
					break;
				}
				if(firstAction.endsWith("Right")){
					Platform.runLater(()->displayAlert("HINT", "Move Right !"));
					break;
				}
			}
			
		}
	

	
	
	
	
	
}
