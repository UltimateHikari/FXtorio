package com.hikari.hellofx.entity.service;

import java.util.Set;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.AssemblerModel;

public class AssemblerService extends BaseService{
	private static final int PRODUCTION_TIME = 1000;
	
	public AssemblerService(ISuspendable model) {
		super(model);
	}
	//@SuppressWarnings("unused")
	protected void performCycle() throws InterruptedException {
		var model = (AssemblerModel)getModel();
		var resourceFirst = model.getInLeft().get();
		model.notifySubs();
		var resourceSecond = model.getInRight().get();
		model.notifySubs();
		sleep(PRODUCTION_TIME);
		var recipe = model.getCurrentRecipe();
		if(recipe.test(Set.of(resourceFirst, resourceSecond))){
			var item = recipe.produce();
			model.setPayload(item);
			model.notifySubs();
			model.getOut().put(item);
		}
		model.setPayload(null);;
		model.notifySubs();
	}
}
