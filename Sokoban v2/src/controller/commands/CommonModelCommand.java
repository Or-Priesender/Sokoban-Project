package controller.commands;

import java.io.IOException;
import java.util.LinkedList;

import model.Model;

public abstract class CommonModelCommand implements Command {

	protected LinkedList<String> params;
	protected Model model;
	
	public void setParams(LinkedList<String> params)
	{
		this.params=params;
	}

}
