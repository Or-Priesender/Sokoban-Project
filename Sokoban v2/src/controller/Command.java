package controller;


import java.io.IOException;
import java.util.List;

import model.data.Level;

public abstract class Command {
	
	protected List<String> params;
	
	public void setParams(List<String> params)
	{
		this.params = params;
	}
	
	public abstract void execute() throws IOException;
	
	
	
	

}
