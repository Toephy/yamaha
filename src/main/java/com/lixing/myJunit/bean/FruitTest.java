package com.lixing.myJunit.bean;

import java.util.ArrayList;
import java.util.List;

public class FruitTest {

	public static void main(String[] args) {
		Fruit fruit = new Fruit("apple", 2);
		Fruit fruit2 = new Fruit("orange", 2);
		List<Fruit> list = new ArrayList<Fruit>();
		Fruit temp = fruit;
		list.add(temp);
		temp = fruit2;
		list.add(temp);
		for(Fruit i : list) {
			System.out.println(i);
		}
	}

}
