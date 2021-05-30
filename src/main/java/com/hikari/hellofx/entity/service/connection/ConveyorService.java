package com.hikari.hellofx.entity.service.connection;

import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.conveyor.ConnectionEvent;
import com.hikari.hellofx.entity.model.conveyor.Conveyor;

public class ConveyorService extends BasicConnectionService {
	private Conveyor cModel;

	public ConveyorService(ISuspendable model) {
		super(model);
		cModel = (Conveyor) model;
	}

	@Override
	protected void performCycle() throws InterruptedException {
		Item o = cModel.getSrc().get();
		cModel.addEvent(ConnectionEvent.DEPARTED);
		cModel.setPayload(o);
		cModel.notifySubs();
		sleep(cModel.getTravelTime());
		cModel.setPayload(null);
		cModel.getDst().put(o);
		cModel.addEvent(ConnectionEvent.ARRIVED);
		cModel.notifySubs();
	}

}