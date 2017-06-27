package model.data.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import model.data.level.Level;

public class ServerDBManager implements DataBaseManager {

	Socket sock;
	PrintWriter writer;
	BufferedReader reader;
	ObjectInputStream objectReader;
	ObjectOutputStream objectWriter;
	
	public ServerDBManager(Socket connectedSock){
		this.sock = connectedSock;
		initIO();
	}
	
	@Override
	public List getGameSessionTableForLevel(String levelName) {
		writer.println("LevelSessionTable " + levelName);
		writer.flush();
		List table = null;
		try {
			table = (List) objectReader.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return table;
	}

	@Override
	public List getGameSessionTableForUser(String username) {
		writer.println("UserSessionTable " + username);
		writer.flush();
		List table = null;
		try {
			table = (List) objectReader.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}

	@Override
	public void saveUserAndLevel(User user, Level lvl) {
		writer.println("SaveSession");
		writer.flush();
		try {
			objectWriter.writeObject(user);
			objectWriter.writeObject(lvl);
			objectWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void addUser(User u) {
		writer.println("addUser");
		writer.flush();
		try {
			objectWriter.writeObject(u);
			objectWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addLevel(Level l) {
		writer.println("addLevel");
		writer.flush();
		try {
			objectWriter.writeObject(l);
			objectWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(int userId) {
		writer.println("deleteUser " + userId);
		writer.flush();
	}

	@Override
	public void deleteLevel(String levelName) {
		writer.println("deleteLevel " + levelName);
		writer.flush();
	}

	@Override
	public void closeDB() {
		try {
			writer.println("close");
			writer.close();
			objectReader.close();
			objectWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initIO(){
		try {
			 this.writer = new PrintWriter(sock.getOutputStream(),true);
			 reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			 objectWriter = new ObjectOutputStream(sock.getOutputStream());
			 objectReader = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getSolution(Level lvl) {
		try {
		writer.println("getSolution");
			objectWriter.writeObject(lvl);
			objectWriter.flush();
			String solution = reader.readLine();
			return solution;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	

}
