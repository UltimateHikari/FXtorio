package com.hikari.hellofx.entity.service;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.conveyor.ConnectionEvent;
import com.hikari.hellofx.entity.model.conveyor.Conveyor;


public class ConveyorService extends BaseService {

	public ConveyorService(ISuspendable model) {
		super(model);
	}

	@Override
	protected void performCycle() throws InterruptedException {
		var model = (Conveyor) getModel();
		Item o = model.getSrc().get();
		model.addEvent(ConnectionEvent.DEPARTED);
		model.setPayload(o);
		model.notifySubs();
		sleep(model.getTravelTime());
		model.setPayload(null);
		model.getDst().put(o);
		model.addEvent(ConnectionEvent.ARRIVED);
		model.notifySubs();
	}

}
