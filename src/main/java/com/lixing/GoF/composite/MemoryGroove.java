package com.lixing.GoF.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存槽
 * @author Administrator
 *
 */
public class MemoryGroove implements Equipment {

	private final double PRICE 			= 60;
	
	private List<Equipment> equipments;
	
	public MemoryGroove(Equipment ... equipments_) {
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
				System.out.println("内存槽中的硬盘，价格：" + equipment.price());
			} else if (equipment instanceof Memory) {
				System.out.println("内存槽中的内存条，价格：" + equipment.price());
			}
			price += equipment.price();
		}
		System.out.println("内存槽总价格：" + price);
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
