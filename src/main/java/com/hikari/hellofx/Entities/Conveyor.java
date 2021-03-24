package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;

public class Conveyor extends BaseModel implements IConnection, ISuspendable{
	private ConnectionInPoint destination;
	private boolean isTurnedOn = false;
	
	public Conveyor(ConnectionInPoint destination_) {
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
	public Object send() {
		// TODO Auto-generated method stub
		return new Object();
		
	}

	@Override
	public void connectSource(IConnectable o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectDestination(IConnectable o) {
		// TODO Auto-generated method stub
		
	}

}
