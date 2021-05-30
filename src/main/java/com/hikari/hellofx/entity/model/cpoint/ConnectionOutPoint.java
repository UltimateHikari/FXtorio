package com.hikari.hellofx.entity.model.cpoint;

import com.hikari.hellofx.entity.IServiceNotifier;
import com.hikari.hellofx.entity.Item;

public class ConnectionOutPoint extends ConnectionPoint {
	public ConnectionOutPoint(IServiceNotifier entity, Double offsetX, Double offsetY) {
		super(entity, offsetX, offsetY);
	}

	@Override
	public Item get() throws InterruptedException {
		var res = super.get();
		notifyParentService();
		return res;
	}

	@Override
	public Item poll() {
		var res = super.poll();
		if (res != null) {
			notifyParentService();
		}
		return res;
	}
}
