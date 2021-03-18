package com.hikari.hellofx.Entities;

public class ConnectionPoint {
	private final Connectable parentEntity;
	//private final ConnectionType type
	public ConnectionPoint(Connectable entity) {
		parentEntity = entity;
	}
	
	public void connect(Connection connection) {
		parentEntity.connect(connection, this);
	}
}
