package com.hikari.hellofx.Entities.ConnectionPoint;

import java.util.concurrent.Semaphore;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.ILoggable;
import com.hikari.hellofx.Entities.Connectable.IConnectable;
import com.hikari.hellofx.Entities.Connection.IConnection;

/*
 * offset in relative to whole figure
 */

public class ConnectionPoint extends BaseModel implements ILoggable{
	private final Double offsetX;
	private final Double offsetY;
	private double lastViewX; // TODO may be mvc leak??
	private double lastViewY; //
	protected IConnection connection = null;
	private final IConnectable parentEntity;

	private static final int inThreadsCount = 1;
	private final Semaphore isEmpty = new Semaphore(inThreadsCount);
	private final Semaphore isFull = new Semaphore(0);
	private Object heldObject = null;

	public ConnectionPoint(IConnectable entity, Double offsetX_, Double offsetY_) {
		parentEntity = entity;
		offsetX = offsetX_;
		offsetY = offsetY_;
	}

	public boolean isFree() {
		return (connection == null);
	}
	
	private void notifyParent() {
		synchronized(parentEntity) {
			parentEntity.notify();
		}
	}

	public void connect(IConnection connection_) {
		connection = connection_;
		notifyParent();
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
		//TODO add checking for exact connected connection/connectable?
		isFull.acquire();
		//log(this.getName() + " giving " + heldObject.toString());
		Object res = heldObject;
		heldObject = null;
		isEmpty.release();
		return res;
	}

	public void put(Object o) throws InterruptedException {
		isEmpty.acquire();
		heldObject = o;
		//log(this.getName() + " taking " + heldObject.toString());
		isFull.release();
	}
	
	public boolean offer(Object o) {
		if(!isEmpty.tryAcquire()) {
			//log(this.getName() + " -offered ");
			return false;
		} else {
			heldObject = o;
			isFull.release();
			//log(this.getName() + " +offered " + heldObject.toString());
			return true;
		}
	}
	
	public Object poll() {
		if(!isFull.tryAcquire()) {
			//log(this.getName() + " -polled ");
			return null;
		} else {
			//log(this.getName() + " +polled " + heldObject.toString());
			Object res = heldObject;
			heldObject = null;
			notifyParent();
			isEmpty.release();
			return res;
		}
	}
}
