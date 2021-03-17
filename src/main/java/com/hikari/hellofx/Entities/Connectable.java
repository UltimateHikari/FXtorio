package com.hikari.hellofx.Entities;

public interface Connectable {
	public boolean hasFreePoints();
	//public void showFreePoints();
	public void connect(Connectable other, Integer pointId);
}
