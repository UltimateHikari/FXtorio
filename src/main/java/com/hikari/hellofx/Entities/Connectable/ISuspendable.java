package com.hikari.hellofx.Entities.Connectable;

public interface ISuspendable {
	public void turnOff();
	public void turnOn();
	public boolean isOn();
}