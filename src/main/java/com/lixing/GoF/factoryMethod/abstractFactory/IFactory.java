package com.lixing.GoF.factoryMethod.abstractFactory;

import com.lixing.GoF.factoryMethod.Car;

public interface IFactory {
	/**
	 * 生产汽车
	 * @return
	 */
	Car createCar();
	
	/**
	 * 生产摩托车
	 * @return
	 */
	Motorcycle createMotorcycle();
}
