package com.lixing.GoF.template;

public class ComputerB extends IComputer {

	@Override
	protected void setCpu() {
		System.out.println("AMD CPU");
	}

	@Override
	protected void setMouse() {
		System.out.println("罗技鼠标");
	}

	@Override
	protected boolean hook() {
		return false;
	}
}
