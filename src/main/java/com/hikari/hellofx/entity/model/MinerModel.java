package com.hikari.hellofx.entity.model;

import java.util.Collections;
import java.util.List;

public class MinerModel extends BasicEntityModel{
	private static final int PRODUCTION_TIME = 250;
	Object o = null;
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);
	
	@Override
	public List<ConnectionInPoint> getInPoints() {
		return Collections.emptyList();
	}

	@Override
	public List<ConnectionOutPoint> getOutPoints() {
		return packPoints(out);
	}

	@Override
	protected void performCycle() throws InterruptedException {
		sleep(PRODUCTION_TIME);
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
