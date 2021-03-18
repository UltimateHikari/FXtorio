package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;

public class Conveyor extends BaseModel implements Connection, Suspendable{
	private Connectable destination;
	private boolean isTurnedOn = false;
	
	public Conveyor(Connectable destination_) {
		destination = destination_;
		// TODO Auto-generated constructor stub
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
	public boolean getState() {
		return isTurnedOn;
	}

	@Override
	public void recieve(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectSource(Connectable o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectDestination(Connectable o) {
		// TODO Auto-generated method stub
		
	}

}
