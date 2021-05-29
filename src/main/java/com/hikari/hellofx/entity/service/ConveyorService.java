package com.hikari.hellofx.entity.service;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.conveyor.ConnectionEvent;
import com.hikari.hellofx.entity.model.conveyor.Conveyor;

public class ConveyorService extends BaseService {
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
		checkIfDetached();
		cModel.setPayload(null);
		cModel.getDst().put(o);
		cModel.addEvent(ConnectionEvent.ARRIVED);
		cModel.notifySubs();
	}

	private void checkIfDetached() throws InterruptedException {
		if (cModel.isDetached()) {
			// TODO here can complete sending left items
			// but for now just dropping all without excessive offers/polls
			cModel.getDst().disconnect();
			cModel.getSrc().disconnect();
			throw new InterruptedException();
		}
	}
}
