package com.hikari.hellofx.entity.service;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.ConstructorModel;

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
