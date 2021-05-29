package com.hikari.hellofx.entity.service;

import java.util.List;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.MergerModel;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MergerService extends BaseService{
	private static final int BLOCKED_POINTS_TRUST_AMOUNT = 4;
	private int lastPolledIndex = 0;
	public MergerService(ISuspendable model) {
		super(model);
	}
	
	private Item pollNewObject(List<ConnectionInPoint> ins) throws InterruptedException {
		Item o;
		var trust = BLOCKED_POINTS_TRUST_AMOUNT;
		for (;;) {
			for (int i = cycleIndex(lastPolledIndex, ins.size()), j = 0;
					j < ins.size() ;
					i = cycleIndex(i, ins.size()), j++) {
				if (!ins.get(i).isFree() && (o = ins.get(i).poll()) != null) {
					lastPolledIndex = (lastPolledIndex + i) % ins.size();
					return o;
				}
			}
			trust--;
			if (trust == 0) {
				trust = BLOCKED_POINTS_TRUST_AMOUNT;
				// go wait for someone to connect/ become free
				log.info("zzz");
				selfWait();
				log.info("awaken2");
			}
		}
	}
	
	private int cycleIndex(int i, int l) {
		return (i + 1) % l;
	}
	
	private void commutateOneObject(MergerModel model) throws InterruptedException {
		Item o = pollNewObject(model.getIns());
		model.getOut().put(o);
		model.notifySubs();
	}

	protected void performCycle() throws InterruptedException {
		var model = (MergerModel)getModel();
		var amount = model.amountOfConnectedPoints();
		if (amount == 0) {
			selfWait();
		} else {
			for (var i = 0; i < amount; i++) {
				commutateOneObject(model);
			}
		}
	}
}
