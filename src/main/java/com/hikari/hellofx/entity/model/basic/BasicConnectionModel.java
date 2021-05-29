package com.hikari.hellofx.entity.model.basic;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.IConnection;
import com.hikari.hellofx.entity.ISuspendable;

public abstract class BasicConnectionModel extends BaseModel implements IConnection, ISuspendable{
	private boolean isDetached = false;
	
	@Override
	public synchronized void turnOff() {
		//always on
	}

	@Override
	public synchronized void turnOn() {
		//always on
	}

	@Override
	public synchronized boolean isOn() {
		return true;
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
