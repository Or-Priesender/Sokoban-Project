package controller;

import java.io.IOException;

import model.data.Level;

public interface Command {
	
	public void execute() throws IOException;

}
