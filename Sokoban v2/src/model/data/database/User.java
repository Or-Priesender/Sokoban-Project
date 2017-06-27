package model.data.database;

import java.io.Serializable;

public class User implements Serializable{
	
	private int userId;
	private String username;
	
	
	public User(String username){
		
		this.username = username;
		
	}
	
	public User() {
		
		username="NA";
		
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public boolean equals(Object obj) {
		User u = (User) obj;
		if(this.username.equalsIgnoreCase(u.getUsername())){
			return true;
		}
		else return false;
	}
	
	@Override
	public int hashCode(){
		return username.hashCode();
	}
	
	
	
	

}
