package com.lixing.GoF.factoryMethod.abstractFactory;

import com.lixing.GoF.factoryMethod.Car;

public class Test {

	public static void main(String[] args) {
		IFactory factory = new BMWFactory();
		Car car = factory.createCar();
		Motorcycle motorcycle = factory.createMotorcycle();
		car.run();
		motorcycle.run();
	}

}
