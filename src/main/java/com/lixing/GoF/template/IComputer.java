package com.lixing.GoF.template;

public abstract class IComputer {
	
	private void setScreen() {
		System.out.println("三星显示器");
	}
	
	private void setMemory() {
		System.out.println("金士顿内存条");
	}
	
	protected abstract void setCpu();

	protected abstract void setMouse();
	
	protected boolean hook() {
		return true;
	}
	
	public final void assembleComputer() {
		setScreen();
		setMemory();
		setCpu();
		if (hook()) {
			setMouse();
		}
	}
}
