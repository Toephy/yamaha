package com.lixing.GoF.factoryMethod.simpleFactory;

import com.lixing.GoF.factoryMethod.Car;

public class Test {

	public static void main(String[] args) {
		Car car = CarFactory.createCar("Benz");
		car.run();
	}

}
