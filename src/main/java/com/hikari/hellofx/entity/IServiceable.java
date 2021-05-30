package com.hikari.hellofx.entity;

import com.hikari.hellofx.base.BaseService;

public interface IServiceable {
	public void connectService(BaseService service);
	public void disconnectService();
	public void notifyService();
}
