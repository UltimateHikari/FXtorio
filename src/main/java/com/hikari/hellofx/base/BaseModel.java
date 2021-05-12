package com.hikari.hellofx.base;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class BaseModel {
	private static Integer lastId = 0;
	private final Integer id;
	private final Set<IModelSubscriber> subscribers = new CopyOnWriteArraySet<>();

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
