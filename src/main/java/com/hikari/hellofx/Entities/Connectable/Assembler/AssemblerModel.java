package com.hikari.hellofx.Entities.Connectable.Assembler;

import java.util.List;

import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityModel;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

public class AssemblerModel extends BasicEntityModel {
	private static final int PRODUCTION_TIME = 1000;
	private Object resourceLeft = null;
	private Object resourceRight = null;
	private Object o = null;

	
	private final ConnectionInPoint inLeft = new ConnectionInPoint(this, -0.5, 0.3);
	private final ConnectionInPoint inRight = new ConnectionInPoint(this, -0.5, -0.3);
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);
	
	@Override
	public List<ConnectionInPoint> getInPoints() {
		return packPoints(inLeft, inRight);
	}

	@Override
	public List<ConnectionOutPoint> getOutPoints() {
		return packPoints(out);
	}
	
	protected void performCycle() throws InterruptedException {
		resourceLeft = inLeft.get();
		notifySubs();
		resourceRight = inRight.get();
		notifySubs();
		sleep(PRODUCTION_TIME);
		o = new Object();
		out.put(o);
		o = null;
		resourceLeft = null;
		resourceRight = null;
		notifySubs();
	}

	@Override
	public Integer getFillCount() {
		return o == null ? 0 : 1;
	}

}
