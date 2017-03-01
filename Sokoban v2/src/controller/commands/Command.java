package controller.commands;

import java.io.IOException;
import java.util.LinkedList;

public interface Command {
	
	/*
	 * General interface for commands. the setParams method is for a case more specific parameters are needed
	 */
	public void execute() throws IOException;
	public void setParams(LinkedList<String> params);

}
