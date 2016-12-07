package com.lixing.myJunit.proxy;

public class CountImpl implements CountInterface {

	public int add() {
		System.out.println("-----------ADD--------------" + this.hashCode());
		return 1;
	}

	public String substract() {
		System.out.println("-----------SUBSTRACT--------------" + this.hashCode());
		return "aa";
	}
	
}
