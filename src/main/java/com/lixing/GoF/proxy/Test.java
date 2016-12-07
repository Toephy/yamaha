package com.lixing.GoF.proxy;

public class Test {

	public static void main(String[] args) {
		TicketSeller seller = new TicketProxy();
		seller.sellTicket();
		System.out.println("--------------------------");
		
		RailwayStation rs= new RailwayStation();
		TicketSeller jdkProxySeller = (TicketSeller)JdkProxy.getProxy(rs);
		jdkProxySeller.sellTicket();
		System.out.println("--------------------------");
		
		RailwayStation cglibProxySeller = (RailwayStation)new CglibProxy(rs).getProxy();
		cglibProxySeller.sellTicket();
	}
}
