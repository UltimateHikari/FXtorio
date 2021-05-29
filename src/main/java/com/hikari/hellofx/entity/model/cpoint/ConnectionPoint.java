package com.hikari.hellofx.entity.model.cpoint;

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
	private double lastViewX;
	@Getter
	private double lastViewY; 
	@Getter
	protected IConnection connection = null;
	private final IServiceNotifier parentEntity;

	private static final int INTHREADSCOUNT = 1;
	private final Semaphore isEmpty = new Semaphore(INTHREADSCOUNT);
	private final Semaphore isFull = new Semaphore(0);
	private Item heldObject = null;

	public ConnectionPoint(IServiceNotifier entity, Double offsetX, Double offsetY) {
		parentEntity = entity;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
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

	public void connect(IConnection connection) {
		this.connection = connection;
		notifyParentService();
	}

	public void disconnect() {
		connection = null;
	}

	public void setLastViewX(Double lastViewX) {
		this.lastViewX = lastViewX;
	}

	public void setLastViewY(Double lastViewY) {
		this.lastViewY = lastViewY;
	}

	public Item get() throws InterruptedException {
		// TODO add checking for exact connected connection/connectable?
		isFull.acquire();
		log.debug(" giving " + heldObject.toString());
		var res = heldObject;
		heldObject = null;
		isEmpty.release();
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
