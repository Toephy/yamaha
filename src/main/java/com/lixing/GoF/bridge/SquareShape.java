package com.lixing.GoF.bridge;

public class SquareShape extends Shape {

	public SquareShape(Color color) {
		super(color);
		this.setShape("方形");
	}

	@Override
	void drawShape() {
		System.out.print("这是一个");
		color.drawColor();
		System.out.println("的" + this.getShape());
	}

}
