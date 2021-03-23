package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;

/*
 * offset in relative to whole figure
 */

public class ConnectionPoint extends BaseModel{
	protected final IConnectable parentEntity;
	private final Double offsetX; 
	private final Double offsetY;
	protected IConnection connection = null;

	public ConnectionPoint(IConnectable entity, Double offsetX_, Double offsetY_) {
		parentEntity = entity;
		offsetX = offsetX_;
		offsetY = offsetY_;
	}
	
	public boolean isFree() {
		return(connection == null);
	}
	
	public void connect(IConnection connection_) {
		connection = connection_;
	}
	
	public void disconnect() {
		//connection.disconnect()?
		connection = null;
	}
	public Double getOffsetX() {
		return offsetX;
	}
	public Double getOffsetY() {
		return offsetY;
	}
}
