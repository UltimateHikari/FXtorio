package com.hikari.hellofx.Entities.Connectable.Miner;

import java.util.ArrayList;

import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityModel;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

public class MinerModel extends BasicEntityModel{
	private final int productionTime = 1000;
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
