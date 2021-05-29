package com.hikari.hellofx.entity;

import java.util.List;

import com.hikari.hellofx.entity.model.ConnectableState;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;
import com.hikari.hellofx.entity.model.ConnectionPoint;

public interface IConnectable extends ISuspendable {
	public ConnectableState getConnectableState();

	public void setConnectableState(ConnectableState state_);

	public List<ConnectionInPoint> getInPoints();

	public List<ConnectionOutPoint> getOutPoints();

	public List<ConnectionPoint> filterFreePoints(Class<? extends ConnectionPoint> point);

	public Integer getFillCount();
}
