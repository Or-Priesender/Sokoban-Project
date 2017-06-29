package controller.commands;

import java.util.LinkedList;

import view.View;

/**
 * This class is made for commands that need information from the View layer.
 * This way more specific commands can inherit this class and instantiate a View of their choosing.
 * @author Or Priesender
 *
 */
public abstract class CommonViewCommand implements Command {

	protected LinkedList<String> params;
	protected View view;

	@Override
	public void setParams(LinkedList<String> params) {
		
		this.params=params;

	}

}
