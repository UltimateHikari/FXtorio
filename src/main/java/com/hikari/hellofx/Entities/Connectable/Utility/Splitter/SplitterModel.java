package com.hikari.hellofx.Entities.Connectable.Utility.Splitter;

import java.util.ArrayList;

import com.hikari.hellofx.Entities.Connectable.Utility.BasicUtilityEntityModel;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

public class SplitterModel extends BasicUtilityEntityModel{	
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
	private final ConnectionOutPoint outFirst = new ConnectionOutPoint(this, 0.5, 0.3);
	private final ConnectionOutPoint outSecond = new ConnectionOutPoint(this, 0.5, -0.3);
	
	@Override
	public ArrayList<ConnectionInPoint> getInPoints() {
		return packPoints(in);
	}

	@Override
	public ArrayList<ConnectionOutPoint> getOutPoints() {
		return packPoints(outFirst, outSecond);
	}
	
	protected void performCycle() throws InterruptedException {
		Object o;
		if(outFirst.isFree() && outSecond.isFree()) {
			System.out.println("zzz");
			synchronized (this) {
				wait();
			}
		}
		if(!outFirst.isFree()) {
			o = in.get();
			notifySubs();
			outFirst.put(o);
			notifySubs();
		}
		if(!outSecond.isFree()) {
			o = in.get();
			notifySubs();
			outSecond.put(o);
			notifySubs();
		}
	}

	@Override
	public Integer getFillCount() {
		//nothing stays inside
		return 0;
	}
	
}
