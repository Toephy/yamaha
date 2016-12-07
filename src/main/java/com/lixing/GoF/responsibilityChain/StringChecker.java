package com.lixing.GoF.responsibilityChain;

public class StringChecker extends ParamChecker {

	@Override
	boolean checkParam(String param) {
		System.out.println("字符串校验:" + param);
		if (this.getNextChecker() != null) {
			this.getNextChecker().checkParam(param);
		}
		return true;
	}

}
