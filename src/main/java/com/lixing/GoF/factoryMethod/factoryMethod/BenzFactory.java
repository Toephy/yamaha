package com.lixing.GoF.factoryMethod.factoryMethod;

import com.lixing.GoF.factoryMethod.BenzCar;
import com.lixing.GoF.factoryMethod.Car;

public class BenzFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new BenzCar();
	}

}
