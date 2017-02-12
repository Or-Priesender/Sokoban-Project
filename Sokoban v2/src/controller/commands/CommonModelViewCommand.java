package controller.commands;



import java.util.LinkedList;
import model.Model;
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
