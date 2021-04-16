package com.hikari.hellofx.Entities;

public interface IConnection {
	public void recieve(Object o);
	public Object send();
	public void connectDestination(ConnectionInPoint o);
}
