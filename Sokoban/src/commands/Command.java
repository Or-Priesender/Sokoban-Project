package commands;

import java.io.IOException;

import levels.Level;

public interface Command {
	
	public void execute() throws IOException;

}
