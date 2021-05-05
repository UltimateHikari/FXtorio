package com.hikari.hellofx.entity;

import com.hikari.hellofx.base.BaseService;

public interface ISuspendable {
	public void turnOff();
	public void turnOn();
	public boolean isOn();
	public void connectService(BaseService service);
	public void disconnectService();
}
