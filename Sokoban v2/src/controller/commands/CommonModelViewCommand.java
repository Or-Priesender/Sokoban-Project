package controller.commands;



import java.util.LinkedList;
import model.Model;
import view.View;

/**
 * This class is made for commands that need information from the Model layer AND the View layer.
 * This way more specific commands can inherit this class and instantiate a Model/View of their choosing.
 * @author Or Priesender
 *
 */

public abstract class CommonModelViewCommand implements Command {
	
	protected LinkedList<String> params;
	protected Model model;
	protected View view;
	
	public void setParams(LinkedList<String> params)
	{
		this.params = params;
	}
	
	@Override
	public String toString() {
		String s = "" + this.getClass() + " " + params.toString();
		return s;
	}
	
	
	
	
	

}
