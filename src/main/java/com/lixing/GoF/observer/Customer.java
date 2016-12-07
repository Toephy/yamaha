package com.lixing.GoF.observer;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 麻辣烫顾客类，监听柜台喊号
 * @author Administrator
 *
 */
public class Customer implements Observer {

	private int number;
	
	public Customer(Observable o, int number) {
		o.addObserver(this);
		this.setNumber(number);
	}

	@Override
	public void update(Observable o, Object arg) {
		List<Integer> numbers = ((ServiceBar)o).getNumbers();
		if (numbers.contains(number)) {
			System.out.println(number + "号顾客收到通知，马上来取餐...");
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
