package controller.commands;

import java.io.IOException;
import java.util.LinkedList;

public interface Command {
	
	public void execute() throws IOException;
	public void setParams(LinkedList<String> params);

}
