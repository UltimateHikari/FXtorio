package com.hikari.hellofx.Entities.Connectable;

import java.util.List;

import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

public interface IConnectable extends ISuspendable{
	public boolean hasFreePoints();
	public ConnectableState getConnectableState();
	public void setConnectableState(ConnectableState state_);
	public List<ConnectionInPoint> getInPoints();
	public List<ConnectionOutPoint> getOutPoints();
	public Integer getFillCount();
	
	public void turnOff();
	public void turnOn();
	public boolean isOn();
}
