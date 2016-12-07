package com.lixing.GoF.factoryMethod.factoryMethod;

import com.lixing.GoF.factoryMethod.Car;

public class Test {

	public static void main(String[] args) {
		CarFactory carFactory = new BenzFactory();
		Car car = carFactory.createCar();
		car.run();
	}

}
