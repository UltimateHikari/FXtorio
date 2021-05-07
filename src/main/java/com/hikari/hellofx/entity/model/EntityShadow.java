package com.hikari.hellofx.entity.model;

import com.hikari.hellofx.base.BaseModel;

import lombok.Getter;

@Getter
public class EntityShadow extends BaseModel {
	private double x = 0.0;
	private double y = 0.0;

	public void move(double x_, double y_) {
		x = x_;
		y = y_;
		notifySubs();
	}
}
