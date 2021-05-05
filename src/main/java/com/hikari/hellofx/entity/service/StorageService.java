package com.hikari.hellofx.entity.service;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.StorageModel;

public class StorageService extends BaseService{
	
	public StorageService(ISuspendable model) {
		super(model);
	}

	protected void performCycle() throws InterruptedException {
		var model = (StorageModel)getModel();	
		Object o = model.getIn().get();
		model.getStorage().add(o);
		model.notifySubs();
	}
}
