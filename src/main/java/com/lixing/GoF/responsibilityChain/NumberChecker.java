package com.lixing.GoF.responsibilityChain;


public class NumberChecker extends ParamChecker {

	@Override
	boolean checkParam(String param) {
		System.out.println("数字校验:" + param);
		if (this.getNextChecker() != null) {
			this.getNextChecker().checkParam(param);
		}
		return true;
	}

	
}
