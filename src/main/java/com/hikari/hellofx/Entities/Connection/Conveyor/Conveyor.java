package com.hikari.hellofx.Entities.Connection.Conveyor;

import java.util.concurrent.ArrayBlockingQueue;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.Connectable.ISuspendable;
import com.hikari.hellofx.Entities.Connection.ConnectionEvent;
import com.hikari.hellofx.Entities.Connection.IConnection;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

public class Conveyor extends BaseModel implements IConnection, ISuspendable{
	private static final int EVENTS_SIZE = 10;
	private ConnectionInPoint dst;
	private ConnectionOutPoint src;
	private boolean isTurnedOn = false;
	private long travelTime = 1000;
	private ArrayBlockingQueue<ConnectionEvent> events = new ArrayBlockingQueue<ConnectionEvent>(EVENTS_SIZE);
	
	public Conveyor() {
		//lolwhat
	}
	
	public Conveyor(ConnectionOutPoint source, ConnectionInPoint destination) {
		connectDestination(destination);
		destination.connect(this);
		connectSource(source);
		source.connect(this);
	}
	
	@Override
	public void turnOff() {
		isTurnedOn = false;
	}

	@Override
	public void turnOn() {
		isTurnedOn = true;
	}

	@Override
	public boolean isOn() {
		return isTurnedOn;
	}

	@Override
	public void connectDestination(ConnectionInPoint o) {
		dst = o;
	}
	
	public void connectSource(ConnectionOutPoint o) {
		src = o;
	}
	
	public long getTravelTime() {
		return travelTime;
	}
	
	public void run() {
		while(true) {
			try {
				Object o = src.get();
				events.add(ConnectionEvent.DEPARTED);
				notifySubs();
				sleep(travelTime);
				dst.put(o);
				events.add(ConnectionEvent.ARRIVED);
				notifySubs();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public ConnectionEvent getLastConnectionEvent() {
		return events.remove();
	}
}
