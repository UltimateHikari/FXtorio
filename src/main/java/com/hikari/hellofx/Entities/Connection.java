package com.hikari.hellofx.Entities;

public interface Connection {
	public void recieve(Object o);
	public void send(Object o);
	public void connectSource(Connectable o);
	public void connectDestination(Connectable o);
}
