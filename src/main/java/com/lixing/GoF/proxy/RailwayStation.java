package com.lixing.GoF.proxy;

/**
 * 火车站售票厅
 * @author Administrator
 *
 */
public class RailwayStation implements TicketSeller {

	@Override
	public void sellTicket() {
		System.out.println("从火车站售票厅买到的票");
	}

}
