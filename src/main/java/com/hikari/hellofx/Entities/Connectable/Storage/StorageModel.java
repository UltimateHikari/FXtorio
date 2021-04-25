package com.hikari.hellofx.Entities.Connectable.Storage;

import java.util.ArrayDeque;
import java.util.ArrayList;

import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityModel;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

public class StorageModel extends BasicEntityModel{
	private static final int STORAGE_SIZE = 100;
	private final ArrayDeque<Object> storage = new ArrayDeque<Object>(STORAGE_SIZE);
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
	
	@Override
	public ArrayList<ConnectionInPoint> getInPoints() {
		return packPoints(in);
	}

	@Override
	public ArrayList<ConnectionOutPoint> getOutPoints() {
		return new ArrayList<ConnectionOutPoint>();
	}
	
	protected void performCycle() throws InterruptedException {
		Object o = in.get();
		storage.add(o);
		notifySubs();
	}

	@Override
	public Integer getFillCount() {
		return storage.size();
	}
}