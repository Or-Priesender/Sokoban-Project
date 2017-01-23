package controller.commands;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import model.Model;
import model.data.level.Level;
import view.View;

public abstract class CommonModelViewCommand implements Command {
	
	protected LinkedList<String> params;
	protected Model model;
	protected View view;
	
	public void setParams(LinkedList<String> params)
	{
		this.params = params;
	}
	
	
	
	
	
	

}
