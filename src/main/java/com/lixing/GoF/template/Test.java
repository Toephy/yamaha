package com.lixing.GoF.template;

public class Test {

	public static void main(String[] args) {
		IComputer computerA = new ComputerA();
		computerA.assembleComputer();
		
		IComputer computerB = new ComputerB();
		computerB.assembleComputer();
	}

}
