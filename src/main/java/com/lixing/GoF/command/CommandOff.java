package com.lixing.GoF.command;

public class CommandOff implements TvAbstractCommand {

	private Tv tv;
	
	public CommandOff(Tv tv) {
		this.tv = tv;
	}
	
	@Override
	public void execute() {
		tv.turnOff();
	}

	public Tv getTv() {
		return tv;
	}

	public void setTv(Tv tv) {
		this.tv = tv;
	}

}
