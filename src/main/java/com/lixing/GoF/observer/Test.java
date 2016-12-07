package com.lixing.GoF.observer;

public class Test {

	public static void main(String[] args) {
		ServiceBar serviceBar = ServiceBar.getInstance();
		
		Customer customer1 = new Customer(serviceBar, 1);
		Customer customer2 = new Customer(serviceBar, 2);
		Customer customer3 = new Customer(serviceBar, 3);
		Customer customer4 = new Customer(serviceBar, 4);
		Customer customer5 = new Customer(serviceBar, 5);
		
		serviceBar.reportNumber(1,3,5);
	}

}
