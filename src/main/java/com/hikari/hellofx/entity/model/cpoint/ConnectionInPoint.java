package com.hikari.hellofx.entity.model.cpoint;

import com.hikari.hellofx.entity.IServiceNotifier;
import com.hikari.hellofx.entity.Item;

public class ConnectionInPoint extends ConnectionPoint {
	public ConnectionInPoint(IServiceNotifier entity, Double offsetX, Double offsetY) {
		super(entity, offsetX, offsetY);
	}

	@Override
	public void put(Item o) throws InterruptedException {
		super.put(o);
		notifyParentService();
	}

	@Override
	public boolean offer(Item o) {
		var res = super.offer(o);
		if (res) {
			notifyParentService();
		}
		return res;
	}
}
