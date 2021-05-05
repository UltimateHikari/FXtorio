package com.hikari.hellofx.entity;

import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;
import com.hikari.hellofx.entity.model.conveyor.ConnectionEvent;

public interface IConnection {
	public ConnectionEvent getLastConnectionEvent();
	public void connectDestination(ConnectionInPoint o);
	public void connectSource(ConnectionOutPoint o);
}
