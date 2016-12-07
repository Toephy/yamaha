package com.lixing.GoF.composite;

public class Test {

	public static void main(String[] args) {
		Equipment disk1 = new Disk();
		Equipment disk2 = new Disk();
		Equipment disk3 = new Disk();
		
		Equipment memory1 = new Memory();
		Equipment memory2 = new Memory();
		Equipment memory3 = new Memory();
		
		Equipment memGroove1 = new MemoryGroove();
		Equipment memGroove2 = new MemoryGroove(memory1, memory2);
		Equipment memGroove3 = new MemoryGroove(memory1, memory2, memory3);
				
		Equipment chassis = new Chassis(disk1, disk2, disk3, memGroove1, memGroove2, memGroove3);
		System.out.println(chassis.price());
		System.out.println(memGroove1.price());
	}

}
