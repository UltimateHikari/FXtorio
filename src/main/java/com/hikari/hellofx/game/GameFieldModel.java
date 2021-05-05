package com.hikari.hellofx.game;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.ISuspendable;

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
	@Override
	public void connectService(BaseService service) {
		// no service
		
	}
	@Override
	public void disconnectService() {
		// no service
		
	}
	@Override
	public void notifyService() {
		// no service
		
	}
	
}
