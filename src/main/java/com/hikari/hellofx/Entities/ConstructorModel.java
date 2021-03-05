package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;

public class ConstructorModel extends BaseModel implements Runnable, Connectable, PowerConnectable, Suspendable{
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
	public void showFreePoints() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect(Connectable other, Integer pointId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
