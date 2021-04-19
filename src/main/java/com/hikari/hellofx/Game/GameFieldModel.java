package com.hikari.hellofx.Game;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.Connectable.ISuspendable;

/*
 * Responsible for turning on/off event handler for mouse&shadow movement
 * because obviously bad for performance when not spawning
 */

public class GameFieldModel extends BaseModel implements ISuspendable{
	private boolean isSpawning = false;
	@Override
	public void turnOff() {
		isSpawning = false;
		notifySubs();
	}
	@Override
	public void turnOn() {
		isSpawning = true;
		notifySubs();
	}
	@Override
	public boolean isOn() {
		return isSpawning;
	}
}
