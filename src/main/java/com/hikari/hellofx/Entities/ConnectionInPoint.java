package com.hikari.hellofx.Entities;

public class ConnectionInPoint extends ConnectionPoint{
	public ConnectionInPoint(IConnectable entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}
	public Object recieve() {
		return super.connection.send();
	}
}
