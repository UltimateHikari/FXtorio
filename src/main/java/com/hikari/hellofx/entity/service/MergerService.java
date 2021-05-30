package com.hikari.hellofx.entity.service;

import java.util.List;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.MergerModel;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionPoint;

public class MergerService extends BaseService{
	public MergerService(ISuspendable model) {
		super(model);
	}
	
	private void commutate(MergerModel model, List<ConnectionPoint> list) throws InterruptedException {
		Item o;
		for(ConnectionPoint p : list) {
			if((o = p.poll()) != null) {
				model.getOut().put(o);
				model.notifySubs();
			}
		}		
	}

	protected void performCycle() throws InterruptedException {
		var model = (MergerModel) getModel();
		var list = model.filterPoints(ConnectionInPoint.class, (p -> (!p.isFree()) && (!p.isEmpty())));
		if (!list.isEmpty()) {
			commutate(model, list);
		}
	}
}
