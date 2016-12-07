package com.lixing.GoF.factoryMethod.simpleFactory;

import com.lixing.GoF.factoryMethod.BMWCar;
import com.lixing.GoF.factoryMethod.BenzCar;
import com.lixing.GoF.factoryMethod.Car;

public class CarFactory {
	
	public static Car createCar(String brand) {
		Car car = null;
		if (brand.equals("Benz")) {
			car = new BenzCar();
		} else if (brand.equals("BMW")) {
			car = new BMWCar();
		}
		return car;
	}
}
