package com.hikari.hellofx.Entities;

public interface IConnectable {
	public boolean hasFreePoints();
	public void connect(IConnection other, ConnectionPoint point);
}
