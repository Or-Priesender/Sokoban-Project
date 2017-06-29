package view;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.data.database.GameSession;

public class ScoreBoardController extends Observable implements Initializable {
	
	@FXML
	TableView<GameSession> table = new TableView<GameSession>();
	
	@FXML
	TableColumn<GameSession,Integer> sessionIdCol,userIdCol,stepsCol,timeCol;
	
	@FXML
	TableColumn<GameSession,String> usernameCol,levelNameCol;
	
	@FXML
	private TextField userText,levelText;
	
	ObservableList<GameSession> list;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		PropertyValueFactory<GameSession,Integer> sessionIdFac = new PropertyValueFactory<>("sessionId");
		PropertyValueFactory<GameSession,Integer> userIdFac = new PropertyValueFactory<>("userId");
		PropertyValueFactory<GameSession,String> usernameFac = new PropertyValueFactory<>("username");
		PropertyValueFactory<GameSession,String> levelNameFac = new PropertyValueFactory<>("levelName");
		PropertyValueFactory<GameSession,Integer> stepsFac = new PropertyValueFactory<>("steps");
		PropertyValueFactory<GameSession,Integer> timeFac = new PropertyValueFactory<>("time");
		
		sessionIdCol.setCellValueFactory(sessionIdFac);
		userIdCol.setCellValueFactory(userIdFac);
		usernameCol.setCellValueFactory(usernameFac);
		levelNameCol.setCellValueFactory(levelNameFac);
		stepsCol.setCellValueFactory(stepsFac);
		timeCol.setCellValueFactory(timeFac);
		
		
		levelText.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER){
					String searchLine = levelText.getText();
					LinkedList<String> params = new LinkedList<String>();
					params.add("DB");
					params.add("loadLevelSession");
					params.add(searchLine);
					setChanged();
					notifyObservers(params);
					
				}
				
			}
		});
		userText.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER){
					String searchLine = userText.getText();
					LinkedList<String> params = new LinkedList<String>();
					params.add("DB");
					params.add("loadUserSession");
					params.add(searchLine);
					setChanged();
					notifyObservers(params);
				}
				
			}
			
			
		});
	}
	
	
	public void setTable(List<GameSession> data){
		
		list = FXCollections.observableArrayList(data);
		table.setItems(this.list);
		table.autosize();
		table.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
					GameSession g = (GameSession) table.getSelectionModel().selectedItemProperty().get();
					LinkedList<String> params = new LinkedList<String>();
					params.add("DB");
					params.add("loadUserSession");
					params.add(g.getUsername());
					
					setChanged();
					notifyObservers(params);
					
				}
				
			}
			
			
			
		});
	}

}
