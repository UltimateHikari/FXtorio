package com.hikari.hellofx.entity.model.basic;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.IConnection;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;

public abstract class BasicConnectionModel extends BaseModel implements IConnection, ISuspendable{
	private boolean isDetached = false;
	
	public abstract ConnectionInPoint getDst();
	public abstract ConnectionOutPoint getSrc();
	
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
