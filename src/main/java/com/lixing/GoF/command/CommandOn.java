package com.lixing.GoF.command;

public class CommandOn implements TvAbstractCommand {

	private Tv tv;
	
	public CommandOn(Tv tv) {
		this.tv = tv;
	}

	@Override
	public void execute() {
		tv.turnOn();
	}

	public Tv getTv() {
		return tv;
	}

	public void setTv(Tv tv) {
		this.tv = tv;
	}

}
