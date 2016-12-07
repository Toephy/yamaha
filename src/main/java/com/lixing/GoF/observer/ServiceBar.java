package com.lixing.GoF.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 麻辣烫餐台喊号，喊到号的顾客前去取餐
 * @author Administrator
 *
 */
public class ServiceBar extends Observable {
	
	private List<Integer> numbers = new ArrayList<Integer>();

	// 餐台是单例的
	private ServiceBar() {}
	
	private static ServiceBar serviceBar = null;
	private static Object classLock = ServiceBar.class;
	
	public static ServiceBar getInstance() {
		if (serviceBar == null) {
			synchronized (classLock) {
				if (serviceBar == null)
					serviceBar = new ServiceBar();
			}
		}
		return serviceBar;
	}
	
	public boolean reportNumber(int ... number) {
		try {
			numbers.clear();
			for (int i : number) {
				numbers.add(i);
			}
			setChanged();
			notifyObservers();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
