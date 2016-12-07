package com.lixing.GoF.template;

public class ComputerA extends IComputer {

	@Override
	protected void setCpu() {
		System.out.println("Intel CPU");
	}

	@Override
	protected void setMouse() {
		System.out.println("牧马人鼠标");
	}

}
