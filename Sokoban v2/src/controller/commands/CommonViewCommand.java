package controller.commands;

import java.util.LinkedList;

import view.View;

public abstract class CommonViewCommand implements Command {

	protected LinkedList<String> params;
	protected View view;

	@Override
	public void setParams(LinkedList<String> params) {
		
		this.params=params;

	}

}
