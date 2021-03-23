package com.hikari.hellofx.Entities;

import java.util.ArrayList;

public interface IConnectable {
	public boolean hasFreePoints();
	public ConnectableState getConnectableState();
	public void setConnectableState(ConnectableState state_);
	public ArrayList<ConnectionInPoint> getInPoints();
	public ArrayList<ConnectionOutPoint> getOutPoints();
	public void connect(IConnection other, ConnectionPoint point);
}
