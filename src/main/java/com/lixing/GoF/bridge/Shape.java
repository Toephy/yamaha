package com.lixing.GoF.bridge;

public abstract class Shape {
	
	private String shape = "未知形状";
	
	Color color;
	
	public Shape(Color color) {
		super();
		this.color = color;
	}

	abstract void drawShape();
	
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

}
