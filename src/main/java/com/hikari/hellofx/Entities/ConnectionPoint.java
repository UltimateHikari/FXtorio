package com.hikari.hellofx.Entities;

public class ConnectionPoint {
	protected final IConnectable parentEntity;
	protected IConnection connection = null;

	public ConnectionPoint(IConnectable entity) {
		parentEntity = entity;
	}
	
	public boolean isFree() {
		return(connection == null);
	}
	
	public void connect(IConnection connection_) {
		connection = connection_;
	}
	
	public void disconnect() {
		//connection.disconnect()?
		connection = null;
	}
}
