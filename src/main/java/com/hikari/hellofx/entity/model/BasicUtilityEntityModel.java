package com.hikari.hellofx.entity.model;

public abstract class BasicUtilityEntityModel extends BasicEntityModel {
	
	protected BasicUtilityEntityModel() {
		turnOn();
	}
	
	@Override
	public void turnOff() {
		super.turnOn();
	}
	
}
