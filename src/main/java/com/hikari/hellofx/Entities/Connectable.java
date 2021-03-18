package com.hikari.hellofx.Entities;

public interface Connectable {
	public boolean hasFreePoints();
	public void connect(Connection other, ConnectionPoint point);
}
