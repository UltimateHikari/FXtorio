package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;

public class Conveyor extends BaseModel implements IConnection, ISuspendable{
	private ConnectionInPoint dst;
	private ConnectionOutPoint src;
	private boolean isTurnedOn = false;
	private long travelTime = 1000;
	private ConnectionEvent lastEvent = null;
	
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
				lastEvent = ConnectionEvent.DEPARTED;
				notifySubs();
				sleep(travelTime);
				dst.put(o);
				lastEvent = ConnectionEvent.ARRIVED;
				notifySubs();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public ConnectionEvent getLastConnectionEvent() {
		return lastEvent ;
	}
}
