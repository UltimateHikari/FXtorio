package com.hikari.hellofx.entity.model;

import com.hikari.hellofx.entity.IServiceNotifier;

public class ConnectionInPoint extends ConnectionPoint{
	public ConnectionInPoint(IServiceNotifier entity, Double offsetX, Double offsetY) {
		super(entity, offsetX, offsetY);
	}
}
