package com.hikari.hellofx.entity;

import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;

public interface IConnection {
	public void connectDestination(ConnectionInPoint o);
	public void connectSource(ConnectionOutPoint o);
}
