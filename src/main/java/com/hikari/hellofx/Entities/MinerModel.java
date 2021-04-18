package com.hikari.hellofx.Entities;

import java.util.ArrayList;

public class MinerModel extends BasicEntityModel{
	private int productionTime = 1000;
	Object o = null;
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);
	
	@Override
	public ArrayList<ConnectionInPoint> getInPoints() {
		//TODO think about sending immutable list
		return new ArrayList<ConnectionInPoint>();
	}

	@Override
	public ArrayList<ConnectionOutPoint> getOutPoints() {
		return packPoints(out);
	}

	@Override
	protected void performCycle() throws InterruptedException {
		sleep(productionTime);
		o = new Object();
		notifySubs();
		out.put(o);
		o = null;
		notifySubs();
	}

	@Override
	public Integer getFillCount() {
		return o == null ? 0 : 1;
	}
	
}
