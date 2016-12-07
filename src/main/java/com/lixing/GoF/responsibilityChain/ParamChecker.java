package com.lixing.GoF.responsibilityChain;

public abstract class ParamChecker {

	ParamChecker nextChecker;

	abstract boolean checkParam(String param);

	public ParamChecker getNextChecker() {
		return nextChecker;
	}

	public void setNextChecker(ParamChecker nextChecker) {
		this.nextChecker = nextChecker;
	}
}
