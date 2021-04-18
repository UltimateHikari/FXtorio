package com.hikari.hellofx.Entities;

import java.util.concurrent.Semaphore;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.ILoggable;

/*
 * offset in relative to whole figure
 */

public class ConnectionPoint extends BaseModel implements ILoggable{
	private final IConnectable parentEntity;
	private final Double offsetX;
	private final Double offsetY;
	private double lastViewX; // TODO may be mvc leak??
	private double lastViewY; //
	protected IConnection connection = null;

	private static final int inThreadsCount = 1;
	private Semaphore isEmpty = new Semaphore(inThreadsCount);
	private Semaphore isFull = new Semaphore(0);
	private Object heldObject = null;

	public ConnectionPoint(IConnectable entity, Double offsetX_, Double offsetY_) {
		parentEntity = entity;
		offsetX = offsetX_;
		offsetY = offsetY_;
	}

	public boolean isFree() {
		return (connection == null);
	}

	public void connect(IConnection connection_) {
		connection = connection_;
	}

	public void disconnect() {
		// connection.disconnect()?
		connection = null;
	}

	public Double getOffsetX() {
		return offsetX;
	}

	public Double getOffsetY() {
		return offsetY;
	}

	public Double getLastViewX() {
		return lastViewX;
	}

	public Double getLastViewY() {
		return lastViewY;
	}

	public void setLastViewX(Double lastViewX_) {
		lastViewX = lastViewX_;
	}

	public void setLastViewY(Double lastViewY_) {
		lastViewY = lastViewY_;
	}

	public Object get() throws InterruptedException {
		isFull.acquire();
		log(this.getName() + " giving " + heldObject.toString());
		Object res = heldObject;
		heldObject = null;
		isEmpty.release();
		return res;
	}

	public void put(Object o) throws InterruptedException {
		isEmpty.acquire();
		heldObject = o;
		log(this.getName() + " taking " + heldObject.toString());
		isFull.release();
	}
}
