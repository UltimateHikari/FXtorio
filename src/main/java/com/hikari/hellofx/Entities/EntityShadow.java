package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;

public class EntityShadow extends BaseModel{
	private double x = 0.0;
	private double y = 0.0;
	public void move(double x_, double y_) {
		x = x_;
		y = y_;
		notifySubs();
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
}
