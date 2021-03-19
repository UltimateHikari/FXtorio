package com.hikari.hellofx.Entities;

public interface IConnection {
	public void recieve(Object o);
	public void send(Object o);
	public void connectSource(IConnectable o);
	public void connectDestination(IConnectable o);
}
