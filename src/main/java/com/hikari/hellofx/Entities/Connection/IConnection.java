package com.hikari.hellofx.Entities.Connection;

import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

public interface IConnection {
	public ConnectionEvent getLastConnectionEvent();
	public void connectDestination(ConnectionInPoint o);
	public void connectSource(ConnectionOutPoint o);
}
