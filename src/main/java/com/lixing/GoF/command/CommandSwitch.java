package com.lixing.GoF.command;

public class CommandSwitch implements TvAbstractCommand {

	private Tv tv;
	
	public CommandSwitch(Tv tv) {
		this.tv = tv;
	}
	
	@Override
	public void execute() {
		tv.switchChannel();
	}

	public Tv getTv() {
		return tv;
	}

	public void setTv(Tv tv) {
		this.tv = tv;
	}
}
