package com.hikari.hellofx.entity;

import java.util.List;

import com.hikari.hellofx.entity.model.ConnectableState;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionPoint;

public interface IConnectable extends ISuspendable {
	public ConnectableState getConnectableState();

	public void setConnectableState(ConnectableState state);

	public List<ConnectionInPoint> getInPoints();

	public List<ConnectionOutPoint> getOutPoints();

	public List<ConnectionPoint> filterFreePoints(Class<? extends ConnectionPoint> point);

	public Integer getFillCount();
}
