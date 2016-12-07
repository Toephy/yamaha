package com.lixing.GoF.bridge;

public class Test {

	public static void main(String[] args) {
		Shape circle = new CircleShape(new RedColor());
		Shape circle2 = new CircleShape(new BlackColor());
		
		Shape square = new SquareShape(new RedColor());
		Shape square2 = new SquareShape(new BlackColor());
		
		circle.drawShape();
		circle2.drawShape();
		square.drawShape();
		square2.drawShape();
	}

}
