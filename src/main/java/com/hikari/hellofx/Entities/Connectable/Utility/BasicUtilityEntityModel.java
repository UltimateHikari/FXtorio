package com.hikari.hellofx.Entities.Connectable.Utility;

import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityModel;

public abstract class BasicUtilityEntityModel extends BasicEntityModel {
	
	protected BasicUtilityEntityModel() {
		turnOn();
	}
	
	@Override
	public void turnOff() {
		super.turnOn();
	}
	
}
