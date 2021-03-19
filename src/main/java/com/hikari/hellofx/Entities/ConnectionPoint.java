package com.hikari.hellofx.Entities;

public class ConnectionPoint {
	private final IConnectable parentEntity;
	//private final ConnectionType type
	public ConnectionPoint(IConnectable entity) {
		parentEntity = entity;
	}
	
	public void connect(IConnection connection) {
		parentEntity.connect(connection, this);
	}
}
