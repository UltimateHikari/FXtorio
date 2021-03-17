package com.hikari.hellofx;

import com.hikari.hellofx.Base.BaseModel;

public class GameFieldModel extends BaseModel{
	private boolean isSpawning = false;
	public boolean getSpawningState() {
		return isSpawning;
	}
	public void setSpawningState(boolean isSpawning_) {
		isSpawning = isSpawning_;
		notifySubs();
	}
}
