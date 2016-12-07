package com.lixing.myJunit.singleton;

public class Singleton {
	
	private Singleton() {
		System.out.println("创建Singleton实例");
	}
	
	//静态内部类只有在使用到的时候才会被加载	
	private static class SingletonHolder {
		private static Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}
	
	public static void getString() {
		System.out.println("123");
	}
	
	public static void main(String[] args) {
		/*Singleton.getString();
		for (int i = 0; i < 10; i++) {
			Singleton.getInstance();
			System.out.println("---------------");
		}*/
		
		String a = "1-2--3";
		String [] a_ = a.split("-");
		for (String c : a_) {
			System.out.println(c);
		}
	}
}
