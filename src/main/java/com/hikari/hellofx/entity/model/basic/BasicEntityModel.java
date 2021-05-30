package com.hikari.hellofx.entity.model.basic;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.IPowerConnectable;
import com.hikari.hellofx.entity.IServiceNotifier;
import com.hikari.hellofx.entity.model.ConnectableState;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionPoint;

import lombok.Setter;

public abstract class BasicEntityModel extends BaseModel implements IConnectable, IPowerConnectable, IServiceNotifier {
	@Setter
	private Object payload = null;
	private boolean isTurnedOn = false;
	private BaseService basicService = null;
	private ConnectableState state = ConnectableState.NO_POINTS;

	@Override
	public synchronized void turnOff() {
		isTurnedOn = false;
		super.notifySubs();
	}

	public void notifyService() {
		synchronized (basicService.getMonitor()) {
			basicService.armItemsQueued();
			basicService.getMonitor().notifyAll();
		}
	}

	@Override
	public synchronized void turnOn() {
		isTurnedOn = true;
		notifyService();
		super.notifySubs();
	}

	public synchronized boolean isOn() {
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
		// ConnectionPoint::isFree logic was delegated to filterFreePoints
		return Arrays.asList(args).stream().collect(Collectors.toUnmodifiableList());
	}

	@Override
	public void connectService(BaseService service) {
		basicService = service;
	}

	@Override
	public void disconnectService() {
		basicService = null;
	}

	public List<ConnectionPoint> filterFreePoints(Class<? extends ConnectionPoint> point) {
		return filterPoints(point, ConnectionPoint::isFree);
	}

	public List<ConnectionPoint> filterNonFreePoints(Class<? extends ConnectionPoint> point) {
		return filterPoints(point, (p -> !p.isFree()));
	}

	public List<ConnectionPoint> filterPoints(Class<? extends ConnectionPoint> point,
			Predicate<? super ConnectionPoint> predicate) {
		return (point.equals(ConnectionInPoint.class) ? getInPoints() : getOutPoints()).stream().filter(predicate)
				.collect(Collectors.toUnmodifiableList());
	}

}
