package com.hikari.hellofx.entity.service;

import java.util.List;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.MergerModel;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MergerService extends BaseService{
	private static final int BLOCKED_POINTS_TRUST_AMOUNT = 4;
	public MergerService(ISuspendable model) {
		super(model);
	}
	
	private Object pollOneObject(List<ConnectionInPoint> ins) throws InterruptedException {
		//TODO actually works wrong, prefers first point istead of full loop. rework.
		Object o;
		var trust = BLOCKED_POINTS_TRUST_AMOUNT;
		for (;;) {
			for (ConnectionInPoint p : ins) {
				if (!p.isFree() && (o = p.poll()) != null) {
					return o;
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
	
	private void commutateOneObject(MergerModel model) throws InterruptedException {
		Object o = pollOneObject(model.getIns());
		model.getOut().put(o);
		model.notifySubs();
	}

	protected void performCycle() throws InterruptedException {
		var model = (MergerModel)getModel();
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
