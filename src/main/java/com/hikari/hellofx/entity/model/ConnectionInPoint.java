package com.hikari.hellofx.entity.model;

import com.hikari.hellofx.entity.IServiceable;

public class ConnectionInPoint extends ConnectionPoint{
	public ConnectionInPoint(IServiceable entity, Double offsetX, Double offsetY) {
		super(entity, offsetX, offsetY);
	}
}
