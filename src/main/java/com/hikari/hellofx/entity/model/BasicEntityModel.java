package com.hikari.hellofx.entity.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.IPowerConnectable;
import com.hikari.hellofx.entity.IServiceNotifier;

import lombok.Setter;

public abstract class BasicEntityModel extends BaseModel implements IConnectable, IPowerConnectable, IServiceNotifier {
	@Setter
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

	@Override
	public ConnectableState getConnectableState() {
		return state;
	}

	@Override
	public void setConnectableState(ConnectableState state) {
		this.state = state;
		notifySubs();
	}
	
	@Override
	public synchronized Integer getFillCount() {
		return payload == null ? 0 : 1;
	}

	@SafeVarargs
	protected final <T extends ConnectionPoint> List<T> packPoints(T... args) {
		return Arrays.asList(args).stream().filter(w -> (w.isFree()))
				.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public void connectService(BaseService service) {
		basicService = service;
	}

	@Override
	public void disconnectService() {
		basicService = null;
	}
	
}
