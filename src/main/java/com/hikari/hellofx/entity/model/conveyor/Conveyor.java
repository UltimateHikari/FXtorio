package com.hikari.hellofx.entity.model.conveyor;

import java.util.concurrent.ArrayBlockingQueue;

import com.hikari.hellofx.entity.model.basic.BasicConnectionModel;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;

import lombok.Getter;
import lombok.Setter;

public class Conveyor extends BasicConnectionModel {
	private static final int EVENTS_SIZE = 10;
	@Getter
	private ConnectionInPoint dst;
	@Getter
	private ConnectionOutPoint src;
	@Getter
	@Setter
	private Object payload = null;
	private static final int TRAVEL_TIME = 1000;
	private ArrayBlockingQueue<ConnectionEvent> events = new ArrayBlockingQueue<>(EVENTS_SIZE);

	public Conveyor() {
	}

	public Conveyor(ConnectionOutPoint source, ConnectionInPoint destination) {
		connectDestination(destination);
		connectSource(source);
	}

	@Override
	public void connectDestination(ConnectionInPoint o) {
		dst = o;
		o.connect(this);
	}

	@Override
	public void connectSource(ConnectionOutPoint o) {
		src = o;
		o.connect(this);
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

}
