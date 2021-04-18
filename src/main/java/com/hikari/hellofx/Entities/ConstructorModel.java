package com.hikari.hellofx.Entities;

import java.util.ArrayList;

public class ConstructorModel extends BasicEntityModel{
	private int productionTime = 1000;
	
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
		Object o = in.get();
		sleep(productionTime);
		out.put(o);
	}
}
