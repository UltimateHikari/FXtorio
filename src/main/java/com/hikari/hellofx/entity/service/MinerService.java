package com.hikari.hellofx.entity.service;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.MinerModel;

public class MinerService extends BaseService{
	private static final int PRODUCTION_TIME = 250;
	public MinerService(ISuspendable model) {
		super(model);
	}

	@Override
	protected void performCycle() throws InterruptedException {
		var model = (MinerModel)getModel();
		sleep(PRODUCTION_TIME);
		var o = new Object();
		model.setPayload(o);
		model.notifySubs();
		model.getOut().put(o);
		model.setPayload(o);
		model.notifySubs();
	}
	
}
