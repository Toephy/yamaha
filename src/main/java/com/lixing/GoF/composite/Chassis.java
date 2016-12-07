package com.lixing.GoF.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 机箱类
 * @author Administrator
 *
 */
public class Chassis implements Equipment {

	private final double PRICE 			= 99;
	
	private List<Equipment> equipments;
	
	public Chassis(Equipment ... equipments_) {
		equipments = new ArrayList<Equipment>();
		for (Equipment e : equipments_) {
			equipments.add(e);
		}
	}

	@Override
	public double price() {
		double price = PRICE;
		for (Equipment equipment : equipments) {
			if (equipment instanceof Disk) {
				System.out.println("机箱中的硬盘，价格：" + equipment.price());
			} else if (equipment instanceof Memory) {
				System.out.println("机箱中的内存条，价格：" + equipment.price());
			} else if (equipment instanceof MemoryGroove) {
				System.out.println("机箱中的内存槽，总价格：" + equipment.price());
			}
			price += equipment.price();
		}
		System.out.println("机箱总价格：" + price);
		return price;
	}

	@Override
	public boolean add(Equipment equipment) {
		try {
			equipments.add(equipment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean remove(Equipment equipment) {
		try {
			equipments.remove(equipment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
