package controller.commands;

import java.util.LinkedList;
/*
 * This class is made for commands that need information from the Model layer.
 * This way more specific commands can inherit this class and instantiate a Model of their choosing.
 */

import model.Model;

/**
 * This abstract class is used for Model-only related commands.
 * @author Or Priesender
 *
 */
public abstract class CommonModelCommand implements Command {

	protected LinkedList<String> params;
	protected Model model;
	
	public void setParams(LinkedList<String> params)
	{
		this.params=params;
	}

}
