package com.hikari.hellofx.Base;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class BaseModel extends Thread{
	private Integer property;
	private Collection<IModelSubscriber> subscribers = new CopyOnWriteArrayList<IModelSubscriber>();

	public Integer getProperty() {
		return property;
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
		subscriber.ModelChanged(this);
	}
}
