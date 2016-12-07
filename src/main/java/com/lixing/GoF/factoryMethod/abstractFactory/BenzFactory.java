package com.lixing.GoF.factoryMethod.abstractFactory;

import com.lixing.GoF.factoryMethod.BenzCar;
import com.lixing.GoF.factoryMethod.Car;

public class BenzFactory implements IFactory {

	@Override
	public Car createCar() {
		return new BenzCar();
	}

	@Override
	public Motorcycle createMotorcycle() {
		return new BenzMotorcycle();
	}

}
