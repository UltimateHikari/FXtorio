package com.hikari.hellofx.entity;

import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;

public interface IConnection {
	public void connectDestination(ConnectionInPoint o);
	public void connectSource(ConnectionOutPoint o);
	public void markDetached();
	public boolean isDetached();
}
