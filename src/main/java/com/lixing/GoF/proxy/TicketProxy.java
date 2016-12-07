package com.lixing.GoF.proxy;

/**
 * 火车票代理出售
 * @author Administrator
 *
 */
public class TicketProxy implements TicketSeller {

	private RailwayStation railwayStation = new RailwayStation();
	
	@Override
	public void sellTicket() {
		doBefore();
		railwayStation.sellTicket();
		doAfter();
	}

	private void doBefore() {
		System.out.println("doBefore");		
	}

	private void doAfter() {
		System.out.println("doAfter");
	}

}
