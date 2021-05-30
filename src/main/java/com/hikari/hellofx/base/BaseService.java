package com.hikari.hellofx.base;

import com.hikari.hellofx.entity.ISuspendable;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseService extends Thread{
	private final ISuspendable model;
	@Getter
	private final Object monitor = new Object();
	private boolean hasItemsQueued = false;
	
	protected BaseService(ISuspendable model) {
		this.model = model;
	}
	
	public void armItemsQueued() {
		synchronized(monitor) {
			hasItemsQueued = true;
		}
	}
	
	protected void selfWait() throws InterruptedException {
		synchronized(monitor) {
			while(!hasItemsQueued || !model.isOn()) {
				monitor.wait();
			}
			hasItemsQueued = false;
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
				selfWait();
				performCycle();
			} catch (InterruptedException e) {
				log.error("Interrupted");
				this.interrupt();
				return;
				//TODO conditional variable? methods for safer stop/start/kill
			}
		}
	}
}
