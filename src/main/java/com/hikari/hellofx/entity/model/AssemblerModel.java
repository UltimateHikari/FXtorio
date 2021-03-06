package com.hikari.hellofx.entity.model;

import java.util.List;

import lombok.Getter;

public class AssemblerModel extends BasicEntityModel {
	@Getter
	private final ConnectionInPoint inLeft = new ConnectionInPoint(this, -0.5, 0.3);
	@Getter
	private final ConnectionInPoint inRight = new ConnectionInPoint(this, -0.5, -0.3);
	@Getter
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);

	@Override
	public List<ConnectionInPoint> getInPoints() {
		return packPoints(inLeft, inRight);
	}

	@Override
	public List<ConnectionOutPoint> getOutPoints() {
		return packPoints(out);
	}
}
