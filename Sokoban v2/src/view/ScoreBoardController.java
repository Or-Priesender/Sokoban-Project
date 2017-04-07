package view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.data.database.GameSession;

public class ScoreBoardController implements Initializable {
	
	@FXML
	TableView table = new TableView<GameSession>();
	
	@FXML
	TableColumn<GameSession,Integer> sessionIdCol,userIdCol,stepsCol,timeCol;
	
	@FXML
	TableColumn<GameSession,String> usernameCol,levelNameCol;
	
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
	}
	public void setTable(List data){
		
		list = FXCollections.observableArrayList(data);
		table.setItems(this.list);
	}

}
