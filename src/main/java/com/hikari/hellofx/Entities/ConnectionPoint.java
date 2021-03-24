package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;

/*
 * offset in relative to whole figure
 */

public class ConnectionPoint extends BaseModel{
	private final IConnectable parentEntity;
	private final Double offsetX; 
	private final Double offsetY;
	private double lastViewX; // may be mvc leak??
	private double lastViewY; //
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
	public Double getLastViewX() {
		return lastViewX;
	}
	public Double getLastViewY() {
		return lastViewY;
	}
	public void setLastViewX(Double lastViewX_) {
		lastViewX = lastViewX_;
	}
	public void setLastViewY(Double lastViewY_) {
		lastViewY = lastViewY_;
	}
}
