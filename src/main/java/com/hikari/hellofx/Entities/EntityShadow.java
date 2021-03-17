package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;

import javafx.geometry.Point2D;

public class EntityShadow extends BaseModel{
	private double x = 0.0;
	private double y = 0.0;
	public void move(double x_, double y_) {
		x = x_;
		y = y_;
		notifySubs();
	}
	public Point2D getPosition() {
		return new Point2D(x, y);
	}
}
