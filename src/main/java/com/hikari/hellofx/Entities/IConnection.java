package com.hikari.hellofx.Entities;

public interface IConnection {
	public ConnectionEvent getLastConnectionEvent();
	public void connectDestination(ConnectionInPoint o);
	public void connectSource(ConnectionOutPoint o);
}
