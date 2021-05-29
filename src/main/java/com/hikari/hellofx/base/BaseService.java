package com.hikari.hellofx.base;

import com.hikari.hellofx.entity.ISuspendable;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseService extends Thread{
	private final ISuspendable model;
	@Getter
	private final Object monitor = new Object();
	
	protected BaseService(ISuspendable model) {
		this.model = model;
	}
	
	protected void selfWait() throws InterruptedException {
		synchronized(monitor) {
			while(!model.isOn()) {
				monitor.wait();
			}
		}
	}
	
	protected abstract void performCycle() throws InterruptedException;
	
	protected ISuspendable getModel() {
		return model;
	}
	
	public void safeStop() {
		Thread.currentThread().interrupt();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				selfWait();
				performCycle();
			} catch (InterruptedException e) {
				log.error("Interrupted");
				Thread.currentThread().interrupt();
				return;
				//TODO conditional variable? methods for safer stop/start/kill
			}
		}
	}
}
