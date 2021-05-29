package com.hikari.hellofx.entity.service;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.SplitterModel;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SplitterService extends BaseService{
	private static final int BLOCKED_POINTS_TRUST_AMOUNT = 4;
	public SplitterService(ISuspendable model) {
		super(model);
	}
	
	private void commutateOneObject(SplitterModel model) throws InterruptedException {
		Item o;
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
				log.info("zzz");
				selfWait();
				log.info("awaken2");
			}
		}
	}

	protected void performCycle() throws InterruptedException {
		var model = (SplitterModel)getModel();
		var amount = model.amountOfConnectedPoints();
		if (amount == 0) {
			log.info("zzz");
			selfWait();
			log.info("awaken1");
		} else {
			// TODO what about disconnects?
			for (var i = 0; i < amount; i++) {
				commutateOneObject(model);
			}
		}
	}
}
