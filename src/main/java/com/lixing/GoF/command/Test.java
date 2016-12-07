package com.lixing.GoF.command;

public class Test {

	public static void main(String[] args) {
		Tv tv = new Tv();
		
		TvAbstractCommand commandOn = new CommandOn(tv);
		TvAbstractCommand commandOff = new CommandOff(tv);
		TvAbstractCommand commandSwitch = new CommandSwitch(tv);
		
		CommandInvoker invoker = new CommandInvoker();
		invoker.addCommand(commandOn, commandOff, commandSwitch);
		
		invoker.execute();
	}

}
