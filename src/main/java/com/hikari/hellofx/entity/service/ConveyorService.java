package com.hikari.hellofx.entity.service;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.conveyor.ConnectionEvent;
import com.hikari.hellofx.entity.model.conveyor.Conveyor;


public class ConveyorService extends BaseService {

	public ConveyorService(ISuspendable model) {
		super(model);
	}

	@Override
	protected void performCycle() throws InterruptedException {
		var model = (Conveyor) getModel();
		Object o = model.getSrc().get();
		model.addEvent(ConnectionEvent.DEPARTED);
		model.notifySubs();
		sleep(model.getTravelTime());
		model.getDst().put(o);
		model.addEvent(ConnectionEvent.ARRIVED);
		model.notifySubs();
	}

}
