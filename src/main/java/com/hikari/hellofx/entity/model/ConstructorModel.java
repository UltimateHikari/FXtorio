package com.hikari.hellofx.entity.model;

import java.util.List;

public class ConstructorModel extends BasicEntityModel{
	private final int PRODUCTION_TIME = 1000;
	private Object o = null;
	
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);
	
	@Override
	public List<ConnectionInPoint> getInPoints() {
		return packPoints(in);
	}

	@Override
	public List<ConnectionOutPoint> getOutPoints() {
		return packPoints(out);
	}
	
	protected void performCycle() throws InterruptedException {
		o = in.get();
		notifySubs();
		sleep(PRODUCTION_TIME);
		out.put(o);
		o = null;
		notifySubs();
	}

	@Override
	public Integer getFillCount() {
		return o == null ? 0 : 1;
	}
	
}
