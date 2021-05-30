package com.hikari.hellofx.entity.service.connection;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.basic.BasicConnectionModel;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BasicConnectionService extends BaseService{
	
	protected BasicConnectionService(ISuspendable model) {
		super(model);
	}

	@Override
	public void run() {
		while (true) {
			try {
				performCycle();
			} catch (InterruptedException e) {
				log.error("Interrupted");
				checkIfDetached();
				Thread.currentThread().interrupt();
				return;
				//TODO conditional variable? methods for safer stop/start/kill
			}
		}
	}
	
	private void checkIfDetached(){
		if(getModel() instanceof BasicConnectionModel model) {
			if (model.isDetached()) {
				log.info("detaching");
				// TODO here can complete sending left items
				// but for now just dropping all without excessive offers/polls
				model.getDst().disconnect();
				model.getSrc().disconnect();
			}
		}
	}
}
