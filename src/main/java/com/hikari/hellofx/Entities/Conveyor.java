package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;

public class Conveyor extends BaseModel implements IConnection, ISuspendable{
	private ConnectionInPoint destination;
	private boolean isTurnedOn = false;
	private long travelTime = 1000;
	private Object transiting;
	
	public Conveyor() {
		
	}
	
	public Conveyor(ConnectionInPoint destination_) {
		connectDestination(destination_);
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
	public void recieve(Object o) {
		transiting = o;
	}

	@Override
	public Object send() {
		return transiting;
	}

	@Override
	public void connectDestination(ConnectionInPoint o) {
		destination = o;
	}
	
	public long getTravelTime() {
		return travelTime;
	}
	
	public void run() {
		while(true) {
				try {
					wait();
					synchronized (this) {
						//TODO draw plan of entity/conveyor lifetime with waits
						notifySubs();
						sleep(travelTime);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
