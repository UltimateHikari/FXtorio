package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;

public class ConstructorModel extends BaseModel implements IConnectable, IPowerConnectable, ISuspendable{
	private boolean isTurnedOn = false;
	@Override
	public void turnOff() {
		isTurnedOn = false;
		super.notifySubs();
	}

	@Override
	public void turnOn() {
		isTurnedOn = true;
		super.notifySubs();
	}

	public boolean getState() {
		return isTurnedOn;
	}
	
	@Override
	public boolean hasFreePoints() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connect(IConnection other, ConnectionPoint point) {
		// TODO Auto-generated method stub
		
	}

	public void despawn() {
		System.out.println("goodbye");
	}

}
