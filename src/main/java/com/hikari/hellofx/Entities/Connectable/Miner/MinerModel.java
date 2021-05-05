package com.hikari.hellofx.Entities.Connectable.Miner;

import java.util.Collections;
import java.util.List;

import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityModel;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

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
