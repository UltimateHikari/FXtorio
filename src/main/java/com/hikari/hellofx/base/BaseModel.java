package com.hikari.hellofx.base;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BaseModel extends Thread{
	private static Integer lastId = 0;
	private final Integer id;
	private final Collection<IModelSubscriber> subscribers = new CopyOnWriteArrayList<>();

	protected BaseModel() {
		id = lastId;
		lastId++;
	}
	
	@Override
	public String toString() {
		return "BaseModel@" + id.toString() + ":" + getType();
	}
	private String getType() {
		return this.getClass().getSimpleName();
	}
	
	public void subscribe(IModelSubscriber subscriber) {
		if(subscribers.contains(subscriber)) {
			throw new IllegalArgumentException("already subscribed");
		}
		subscribers.add(subscriber);
	}
	public void unsubscribe(IModelSubscriber subscriber) {
		subscribers.remove(subscriber);
	}
	public void notifySubs() {
		for(IModelSubscriber sub : subscribers) {
			notify(sub);
		}
	}
	private void notify(IModelSubscriber subscriber) {
		subscriber.modelChanged(this);
	}
}
