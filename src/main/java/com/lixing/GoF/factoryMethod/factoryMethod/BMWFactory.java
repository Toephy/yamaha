package com.lixing.GoF.factoryMethod.factoryMethod;

import com.lixing.GoF.factoryMethod.BMWCar;
import com.lixing.GoF.factoryMethod.Car;

public class BMWFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new BMWCar();
	}

}
