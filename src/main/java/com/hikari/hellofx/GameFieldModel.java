package com.hikari.hellofx;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.Suspendable;

/*
 * Responsible for turning on/off event handler for mouse&shadow movement
 * because obviously bad for performance when not spawning
 */

public class GameFieldModel extends BaseModel implements Suspendable{
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
	public boolean getState() {
		return isSpawning;
	}
}
