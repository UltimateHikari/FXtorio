package com.hikari.hellofx.base;

import com.hikari.hellofx.entity.ISuspendable;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public abstract class BaseService extends Thread{
	private final ISuspendable model;
	
	protected void selfWait() throws InterruptedException {
		synchronized(this) {
			wait();
		}
	}
	
	protected abstract void performCycle() throws InterruptedException;
	
	protected ISuspendable getModel() {
		return model;
	}
	
	public void safeStop() {
		this.interrupt();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				while(!model.isOn()) {
					selfWait();
				}
				performCycle();
			} catch (InterruptedException e) {
				log.error("Interrupted");
				return;
				//TODO conditional variable? methods for safer stop/start/kill
			}
		}
	}
}
