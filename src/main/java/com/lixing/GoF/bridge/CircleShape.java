package com.lixing.GoF.bridge;

public class CircleShape extends Shape {

	public CircleShape(Color color) {
		super(color);
		this.setShape("圆形");
	}

	@Override
	void drawShape() {
		System.out.print("这是一个");
		color.drawColor();
		System.out.println("的" + this.getShape());
	}

}
