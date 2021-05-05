package com.hikari.hellofx.entity.model;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;

public class ConstructorService extends BaseService{
	private final int PRODUCTION_TIME = 1000;
	public ConstructorService(ISuspendable model) {
		super(model);
	}

	protected void performCycle() throws InterruptedException {
		var model = (ConstructorModel)getModel();
		var o = model.getIn().get();
		model.setPayload(o);
		model.notifySubs();
		sleep(PRODUCTION_TIME);
		model.getOut().put(o);
		model.setPayload(o);
		model.notifySubs();
	}
}
