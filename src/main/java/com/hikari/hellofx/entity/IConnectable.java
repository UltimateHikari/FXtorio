package com.hikari.hellofx.entity;

import java.util.List;

import com.hikari.hellofx.entity.model.ConnectableState;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;

public interface IConnectable extends ISuspendable{
	public ConnectableState getConnectableState();
	public void setConnectableState(ConnectableState state_);
	public List<ConnectionInPoint> getInPoints();
	public List<ConnectionOutPoint> getOutPoints();
	public Integer getFillCount();
	
	public void turnOff();
	public void turnOn();
	public boolean isOn();
}
