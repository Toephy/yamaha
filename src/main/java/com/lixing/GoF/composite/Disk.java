package com.lixing.GoF.composite;

/**
 * 硬盘
 * @author Administrator
 *
 */
public class Disk implements Equipment {

	private final double PRICE 			= 360;
	
	@Override
	public double price() {
		return PRICE;
	}

	//leaf不需要add实现
	@Override
	public boolean add(Equipment equipment) {
		return false;
	}

	//leaf不需要remove实现
	@Override
	public boolean remove(Equipment equipment) {
		return false;
	}

}
