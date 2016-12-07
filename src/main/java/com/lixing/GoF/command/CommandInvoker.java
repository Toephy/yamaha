package com.lixing.GoF.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
	
	private List<TvAbstractCommand> commands;
	
	public void addCommand(TvAbstractCommand ... commandArray) {
		if (commands == null)
			commands = new ArrayList<TvAbstractCommand>();
		
		for (TvAbstractCommand c : commandArray) {
			commands.add(c);
		}
	}
	
	public void execute() {
		for (TvAbstractCommand c : commands) {
			c.execute();
		}
	}
	
}
