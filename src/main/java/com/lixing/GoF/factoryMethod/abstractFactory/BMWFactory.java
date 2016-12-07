package com.lixing.GoF.factoryMethod.abstractFactory;

import com.lixing.GoF.factoryMethod.BMWCar;
import com.lixing.GoF.factoryMethod.Car;

public class BMWFactory implements IFactory {

	@Override
	public Car createCar() {
		return new BMWCar();
	}

	@Override
	public Motorcycle createMotorcycle() {
		return new BMWMotorcycle();
	}

}
