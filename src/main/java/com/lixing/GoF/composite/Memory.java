package com.lixing.GoF.composite;

/**
 * 内存
 * @author Administrator
 *
 */
public class Memory implements Equipment {

	private final double PRICE 			= 299;

	@Override
	public double price() {
		//System.out.println("内存价格：" + PRICE);
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
