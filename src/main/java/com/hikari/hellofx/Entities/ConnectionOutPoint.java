package com.hikari.hellofx.Entities;

public class ConnectionOutPoint extends ConnectionPoint{
	public ConnectionOutPoint(IConnectable entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}
	public void send(Object o) {
		super.connection.recieve(o);
	}
}
