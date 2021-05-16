package com.hikari.hellofx.entity.model;

import java.util.List;

import lombok.Getter;

public class ConstructorModel extends BasicProducerModel {
	@Getter
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
	@Getter
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);

	@Override
	public List<ConnectionInPoint> getInPoints() {
		return packPoints(in);
	}

	@Override
	public List<ConnectionOutPoint> getOutPoints() {
		return packPoints(out);
	}
}
