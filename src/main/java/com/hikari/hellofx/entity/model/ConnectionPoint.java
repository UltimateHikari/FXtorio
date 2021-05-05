package com.hikari.hellofx.entity.model;

import java.util.concurrent.Semaphore;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.IConnection;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/*
 * offset in relative to whole figure
 */
@Log4j2
public class ConnectionPoint extends BaseModel {
	@Getter
	private final Double offsetX;
	@Getter
	private final Double offsetY;
	@Getter
	private double lastViewX; // TODO may be mvc leak??
	@Getter
	private double lastViewY; //
	protected IConnection connection = null;
	private final IConnectable parentEntity;

	private static final int INTHREADSCOUNT = 1;
	private final Semaphore isEmpty = new Semaphore(INTHREADSCOUNT);
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
		synchronized (parentEntity) {
			log.info("notifying " + parentEntity.toString());
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

	public void setLastViewX(Double lastViewX_) {
		lastViewX = lastViewX_;
	}

	public void setLastViewY(Double lastViewY_) {
		lastViewY = lastViewY_;
	}

	public Object get() throws InterruptedException {
		// TODO add checking for exact connected connection/connectable?
		isFull.acquire();
		log.debug(" giving " + heldObject.toString());
		var res = heldObject;
		heldObject = null;
		isEmpty.release();
		return res;
	}

	public void put(Object o) throws InterruptedException {
		isEmpty.acquire();
		heldObject = o;
		log.debug(" taking " + heldObject.toString());
		isFull.release();
	}

	public boolean offer(Object o) {
		if (!isEmpty.tryAcquire()) {
			log.debug(" -offered ");
			return false;
		} else {
			heldObject = o;
			isFull.release();
			log.debug(" +offered " + heldObject.toString());
			return true;
		}
	}

	public Object poll() {
		if (!isFull.tryAcquire()) {
			log.debug(" -polled ");
			return null;
		} else {
			log.debug(" +polled " + heldObject.toString());
			var res = heldObject;
			heldObject = null;
			notifyParent();
			isEmpty.release();
			return res;
		}
	}
}
