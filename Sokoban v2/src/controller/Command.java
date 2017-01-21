package controller;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import model.data.Level;

public abstract class Command {
	
	protected LinkedList<String> params;
	
	public void setParams(LinkedList<String> params)
	{
		this.params = params;
	}
	
	public abstract void execute() throws IOException;
	
	
	
	

}
