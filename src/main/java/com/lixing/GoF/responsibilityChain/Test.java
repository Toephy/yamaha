package com.lixing.GoF.responsibilityChain;

public class Test {

	public static void main(String[] args) {
		ParamChecker numberChecker = new NumberChecker();
		ParamChecker stringChecker = new StringChecker();
		
		numberChecker.setNextChecker(stringChecker);
		// stringChecker.setNextChecker(numberChecker);
		
		numberChecker.checkParam("123");
	}

}
