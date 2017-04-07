package model.data.database;

import java.util.List;

import model.data.level.Level;

public interface DataBaseManager {
	
	public List getGameSessionTableForLevel(Level lvl);
	public boolean userExists(String username);
	public void saveUserAndLevel(User user, Level lvl);
	public void addUser(User u);
	public void addLevel(Level l);
	public void deleteUser(int userId);
	public void deleteLevel(String levelName);
	public void closeDB();
	
	
	

}
