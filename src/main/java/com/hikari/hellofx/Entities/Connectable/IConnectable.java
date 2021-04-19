package com.hikari.hellofx.Entities.Connectable;

import java.util.ArrayList;

import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

public interface IConnectable {
	public boolean hasFreePoints();
	public ConnectableState getConnectableState();
	public void setConnectableState(ConnectableState state_);
	public ArrayList<ConnectionInPoint> getInPoints();
	public ArrayList<ConnectionOutPoint> getOutPoints();
	public Integer getFillCount();
}
