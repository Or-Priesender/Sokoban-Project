package model.data.database;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.data.level.Level;

/**
 * This bean defines a game session for a user and a level.
 * @author Or Priesender
 *
 */
public class GameSession implements Serializable{
	
	private int sessionId;
	private int userId;
	private String username;
	private String levelName;
	private int steps;
	private int time;
	
	public GameSession(){
	}
	
	public GameSession(User user,Level lvl){
		
		userId = user.getUserId();
		this.username = user.getUsername();
		levelName = lvl.getLevelName();
		steps=lvl.getSteps();
		time = lvl.getTime();
	}
	
	public SimpleIntegerProperty sessionIdProperty(){
		return new SimpleIntegerProperty(sessionId);
		
	}
	
	public SimpleIntegerProperty userIdProperty(){
		return new SimpleIntegerProperty(userId);
		
	}
	
	public SimpleStringProperty usernameProperty(){
		return new SimpleStringProperty(username);
		
	}
	
	public SimpleStringProperty levelNameProperty(){
		return new SimpleStringProperty(levelName);
		
	}
	
	public SimpleIntegerProperty stepsProperty(){
		return new SimpleIntegerProperty(steps);
		
	}
	
	public SimpleIntegerProperty timeProperty(){
		return new SimpleIntegerProperty(time);
		
	}
	
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		
		return this.username.hashCode() - this.levelName.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		GameSession gs = (GameSession) obj;
		return (gs.getSessionId() == this.getSessionId());
	}
	
	
	
	

}
