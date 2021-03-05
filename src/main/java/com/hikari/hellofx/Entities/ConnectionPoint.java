package com.hikari.hellofx.Entities;

enum ConnectionType{
	INPUT_POINT,
	OUTPUT_POINT
}

public class ConnectionPoint {
	private final Connectable parentEntity;
	private final Integer pointId;
	//private final ConnectionType type
	public ConnectionPoint(Integer pointId_, ConnectionType type ,Connectable entity) {
		parentEntity = entity;
		pointId = pointId_;
		// TODO Auto-generated constructor stub
		// add handler to entity.connect(id) on pres
		
	}
	
	public void connect(Connectable otherEntity) {
		parentEntity.connect(otherEntity, pointId);
	}
}
