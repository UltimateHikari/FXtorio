package com.hikari.hellofx.entity.model;

import java.util.concurrent.Semaphore;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.IConnection;
import com.hikari.hellofx.entity.IServiceNotifier;
import com.hikari.hellofx.entity.Item;

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
	private final IServiceNotifier parentEntity;

	private static final int INTHREADSCOUNT = 1;
	private final Semaphore isEmpty = new Semaphore(INTHREADSCOUNT);
	private final Semaphore isFull = new Semaphore(0);
	private Item heldObject = null;

	public ConnectionPoint(IServiceNotifier entity, Double offsetX_, Double offsetY_) {
		parentEntity = entity;
		offsetX = offsetX_;
		offsetY = offsetY_;
	}

	public boolean isFree() {
		return (connection == null);
	}

	private void notifyParentService() {
		synchronized (parentEntity) {
			log.info("notifying " + parentEntity.toString());
			parentEntity.notifyService();
		}
	}

	public void connect(IConnection connection_) {
		connection = connection_;
		notifyParentService();
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

	public Item get() throws InterruptedException {
		// TODO add checking for exact connected connection/connectable?
		isFull.acquire();
		log.debug(" giving " + heldObject.toString());
		var res = heldObject;
		heldObject = null;
		isEmpty.release();
		//notifyParent();
		return res;
	}

	public void put(Item o) throws InterruptedException {
		isEmpty.acquire();
		heldObject = o;
		log.debug(" taking " + heldObject.toString());
		isFull.release();
		notifyParentService();
	}

	public boolean offer(Item o) {
		if (!isEmpty.tryAcquire()) {
			log.debug(" -offered ");
			return false;
		} else {
			heldObject = o;
			isFull.release();
			log.debug(" +offered " + heldObject.toString());
			notifyParentService();
			return true;
		}
	}

	public Item poll() {
		if (!isFull.tryAcquire()) {
			log.debug(" -polled ");
			return null;
		} else {
			log.debug(" +polled " + heldObject.toString());
			var res = heldObject;
			heldObject = null;
			notifyParentService();
			isEmpty.release();
			return res;
		}
	}
	
	//this two for preventing self-connecting with spawner
	IServiceNotifier getParentEntity() {
		return parentEntity;
	}
	
	public boolean parentEquals(ConnectionPoint other) {
		return parentEntity.equals(other.getParentEntity());
	}
}
