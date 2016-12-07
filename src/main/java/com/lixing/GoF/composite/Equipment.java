package com.lixing.GoF.composite;

public interface Equipment {
	
	/**
	 * 设备的原价
	 * @return
	 */
	public abstract double price();
	
	/**
	 * 增加设备部件
	 * @param equipment
	 * @return
	 */
	public abstract boolean add(Equipment equipment);
	
	/**
	 * 移除设备部件
	 * @param equipment
	 * @return
	 */
	public abstract boolean remove(Equipment equipment);

}
