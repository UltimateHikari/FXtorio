package com.hikari.hellofx.entity.service;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.AssemblerModel;

public class AssemblerService extends BaseService{
	private static final int PRODUCTION_TIME = 1000;
	
	public AssemblerService(ISuspendable model) {
		super(model);
	}
	@SuppressWarnings("unused")
	protected void performCycle() throws InterruptedException {
		var model = (AssemblerModel)getModel();
		var resourceLeft = model.getInLeft().get();
		model.notifySubs();
		var resourceRight = model.getInRight().get();
		model.notifySubs();
		sleep(PRODUCTION_TIME);
		var o = Item.IRON;
		model.setPayload(o);
		model.notifySubs();
		model.getOut().put(o);
		model.setPayload(null);;
		model.notifySubs();
	}
}
