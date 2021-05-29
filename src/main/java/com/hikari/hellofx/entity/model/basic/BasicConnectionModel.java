package com.hikari.hellofx.entity.model.basic;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.IConnection;
import com.hikari.hellofx.entity.ISuspendable;

public abstract class BasicConnectionModel extends BaseModel implements IConnection, ISuspendable{
	private boolean isDetached = false;
	
	@Override
	public void turnOff() {
		//always on
	}

	@Override
	public void turnOn() {
		//always on
	}

	@Override
	public boolean isOn() {
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
