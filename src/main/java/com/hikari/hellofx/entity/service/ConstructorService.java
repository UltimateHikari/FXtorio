package com.hikari.hellofx.entity.service;

import java.util.Set;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.ConstructorModel;

public class ConstructorService extends BaseService{
	private static final int PRODUCTION_TIME = 1000;
	public ConstructorService(ISuspendable model) {
		super(model);
	}

	protected void performCycle() throws InterruptedException {
		//TODO: for now throwing away all wrong components due to infinite 
		// mire supply. same in assembler
		var model = (ConstructorModel)getModel();
		var o = model.getIn().get();
		model.setPayload(o);
		model.notifySubs();
		sleep(PRODUCTION_TIME);
		var recipe = model.getCurrentRecipe();
		if(recipe.test(Set.of(o))){
			model.getOut().put(recipe.produce());
		}
		model.setPayload(null);
		model.notifySubs();
	}
}
