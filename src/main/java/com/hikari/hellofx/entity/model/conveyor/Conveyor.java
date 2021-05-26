package com.hikari.hellofx.entity.model.conveyor;

import java.util.concurrent.ArrayBlockingQueue;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.IConnection;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;

import lombok.Getter;
import lombok.Setter;

public class Conveyor extends BaseModel implements IConnection, ISuspendable {
	private static final int EVENTS_SIZE = 10;
	@Getter
	private ConnectionInPoint dst;
	@Getter
	private ConnectionOutPoint src;
	@Getter
	@Setter
	private Object payload = null;
	private static final int TRAVEL_TIME = 1000;
	private ArrayBlockingQueue<ConnectionEvent> events = new ArrayBlockingQueue<ConnectionEvent>(EVENTS_SIZE);
	private boolean isDetached;

	public Conveyor() {
	}

	public Conveyor(ConnectionOutPoint source, ConnectionInPoint destination) {
		connectDestination(destination);
		destination.connect(this);
		connectSource(source);
		source.connect(this);
	}

	@Override
	public void turnOff() {
		// always on
	}

	@Override
	public void turnOn() {
		// always on
	}

	@Override
	public boolean isOn() {
		return true;
	}

	@Override
	public void connectDestination(ConnectionInPoint o) {
		dst = o;
	}

	public void connectSource(ConnectionOutPoint o) {
		src = o;
	}

	public long getTravelTime() {
		return TRAVEL_TIME;
	}

	public ConnectionEvent getLastConnectionEvent() {
		if (!events.isEmpty()) {
			return events.remove();
		} else {
			return ConnectionEvent.NOTHING;
		}
	}

	public void addEvent(ConnectionEvent event) {
		events.add(event);
	}

	@Override
	public void markDetached() {
		isDetached = true;
	}

	@Override
	public boolean isDetached() {
		return isDetached;
	}

}
