package commands;

import invokers.CommonInvoker;

public class ExitCommand implements Command {
	
	CommonInvoker c;

	public ExitCommand(CommonInvoker c) {
		this.c = c;
	}
	@Override
	public void execute() {
		c.execute();

	}

}
