package com.hikari.hellofx.entity.service;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;
import com.hikari.hellofx.entity.model.SplitterModel;

public class SplitterService extends BaseService{
	private static final int BLOCKED_POINTS_TRUST_AMOUNT = 4;
	public SplitterService(ISuspendable model) {
		super(model);
	}
	
	private void commutateOneObject(SplitterModel model) throws InterruptedException {
		Object o;
		var trust = BLOCKED_POINTS_TRUST_AMOUNT;
		o = model.getIn().get();
		model.notifySubs();
		for (;;) {
			for (ConnectionOutPoint p : model.getOuts()) {
				if (!p.isFree() && p.offer(o)) {
					return;
				}
			}
			trust--;
			if (trust == 0) {
				trust = BLOCKED_POINTS_TRUST_AMOUNT;
				// go wait for someone to connect/ become free
				selfWait();
			}
		}
	}

	protected void performCycle() throws InterruptedException {
		var model = (SplitterModel)getModel();
		var amount = model.amountOfConnectedPoints();
		if (amount == 0) {
			selfWait();
		} else {
			// TODO what about disconnects?
			for (var i = 0; i < amount; i++) {
				commutateOneObject(model);
			}
		}
	}
}
