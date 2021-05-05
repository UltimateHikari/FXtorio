package com.hikari.hellofx.Entities.Connectable.Utility.Splitter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hikari.hellofx.Entities.Connectable.Utility.BasicUtilityEntityModel;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

public class SplitterModel extends BasicUtilityEntityModel{	
	private static final int OFFERING_INTERVAL_MS = 100;
	private static final int BLOCKED_POINTS_TRUST_AMOUNT = 4;
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
	private final ConnectionOutPoint outFirst;
	private final ConnectionOutPoint outSecond;
	private final ConnectionOutPoint outThird;
	private final List<ConnectionOutPoint> outs;
	
	public SplitterModel() {
		outFirst = new ConnectionOutPoint(this, 0.0, 0.5);
		outSecond = new ConnectionOutPoint(this, 0.5, 0.0);
		outThird = new ConnectionOutPoint(this, 0.0, -0.5);
		outs = Stream.of(outFirst,outSecond,outThird).collect(Collectors.toList());
	}
	
	@Override
	public ArrayList<ConnectionInPoint> getInPoints() {
		return packPoints(in);
	}

	@Override
	public ArrayList<ConnectionOutPoint> getOutPoints() {
		return packPoints(outFirst, outSecond, outThird);
	}
	
	private int amountOfConnectedPoints() {
		return outs.stream()
				.map((o) -> (o.isFree() ? 0 : 1))
				.reduce(0, (a, b) -> a + b);

	}
	
	private void waitForAvailablePoint() throws InterruptedException {
		synchronized (this) {
			wait();
		}
	}
	
	private void commutateOneObject() throws InterruptedException {
		Object o;
		int trust = BLOCKED_POINTS_TRUST_AMOUNT;
		o = in.get();
		notifySubs();
		for(;;) {
			for(ConnectionOutPoint p : outs) {
				if(!p.isFree() && p.offer(o)) {
					return;
				}
			}
			trust--;
			if(trust == 0) {
				trust = BLOCKED_POINTS_TRUST_AMOUNT;
				//go wait for someone to connect/ become free
				waitForAvailablePoint();
			}
		}	
	}
	protected void performCycle() throws InterruptedException {
		int amount = amountOfConnectedPoints();
		if(amount == 0) {
			waitForAvailablePoint();
		} else {
			//TODO what about disconnects?
			for(int i = 0; i < amount; i++) {
				commutateOneObject();
			}
		}
//		if(outFirst.isFree() && outSecond.isFree()) {
//			
//		}
//		if(!outFirst.isFree()) {
//			o = in.get();
//			notifySubs();
//			outFirst.put(o);
//			notifySubs();
//		}
//		if(!outSecond.isFree()) {
//			o = in.get();
//			notifySubs();
//			outSecond.put(o);
//			notifySubs();
//		}
	}

	@Override
	public Integer getFillCount() {
		//nothing stays inside
		return 0;
	}
	
}
