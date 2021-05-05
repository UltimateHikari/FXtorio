package com.hikari.hellofx.entity.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.IPowerConnectable;
import com.hikari.hellofx.entity.IServiceable;

public abstract class BasicEntityModel extends BaseModel implements IConnectable, IPowerConnectable, IServiceable{
	private Object payload = null;
	private boolean isTurnedOn = false;
	private BaseService basicService = null;
	private ConnectableState state = ConnectableState.NO_POINTS;

	@Override
	public void turnOff() {
		isTurnedOn = false;
		super.notifySubs();
	}

	public void notifyService() {
		synchronized (basicService) {
			basicService.notify();
		}
	}

	@Override
	public void turnOn() {
		isTurnedOn = true;
		notifyService();
		super.notifySubs();
	}

	public boolean isOn() {
		return isTurnedOn;
	}

	public void despawn() {
		// TODO
	}

	@Override
	public ConnectableState getConnectableState() {
		return state;
	}

	@Override
	public void setConnectableState(ConnectableState state_) {
		state = state_;
		notifySubs();
	}
	
	public synchronized void setPayload(Object o) {
		payload = o; 
	}
	
	@Override
	public synchronized Integer getFillCount() {
		return payload == null ? 0 : 1;
	}

	@SafeVarargs
	protected final <T> List<T> packPoints(T... args) {
		return Arrays.asList(args).stream().filter(w -> (((ConnectionPoint) w).isFree()))
				.collect(Collectors.toUnmodifiableList());
	}

	public void connectService(BaseService service) {
		basicService = service;
	}

	public void disconnectService() {
		basicService = null;
	}
}
