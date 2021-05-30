package com.hikari.hellofx.entity.service;

import java.util.List;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.SplitterModel;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionPoint;

public class SplitterService extends BaseService {
	private Item heldItem = null;

	public SplitterService(ISuspendable model) {
		super(model);
	}

	private void commutate(SplitterModel model, List<ConnectionPoint> list) throws InterruptedException {
		for (ConnectionPoint p : list) {
			if (heldItem == null) {
				heldItem = model.getIn().get();
			}
			if (p.offer(heldItem)) {
				heldItem = null;
			}
		}
	}

	protected void performCycle() throws InterruptedException {
		var model = (SplitterModel) getModel();
		var list = model.filterPoints(ConnectionOutPoint.class, (p -> (!p.isFree()) && p.isEmpty()));
		if (!list.isEmpty()) {
			commutate(model, list);
		}
	}
}
