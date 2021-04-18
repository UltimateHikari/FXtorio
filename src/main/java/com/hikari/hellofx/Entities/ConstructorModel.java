package com.hikari.hellofx.Entities;

import java.util.ArrayList;

public class ConstructorModel extends BasicEntityModel{
	private int productionTime = 1000;
	private Object o = null;
	
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);
	
	@Override
	public ArrayList<ConnectionInPoint> getInPoints() {
		return packPoints(in);
	}

	@Override
	public ArrayList<ConnectionOutPoint> getOutPoints() {
		return packPoints(out);
	}
	
	protected void performCycle() throws InterruptedException {
		o = in.get();
		notifySubs();
		sleep(productionTime);
		out.put(o);
		o = null;
		notifySubs();
	}

	@Override
	public Integer getFillCount() {
		return o == null ? 0 : 1;
	}
	
}
