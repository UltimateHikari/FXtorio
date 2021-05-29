package com.hikari.hellofx.entity.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.model.basic.BasicEntityModel;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;

import lombok.Getter;

public class MergerModel extends BasicEntityModel {
	@Getter
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);
	private final ConnectionInPoint inFirst;
	private final ConnectionInPoint inSecond;
	private final ConnectionInPoint inThird;
	@Getter
	private final List<ConnectionInPoint> ins;

	public MergerModel() {
		inFirst = new ConnectionInPoint(this, 0.0, 0.5);
		inSecond = new ConnectionInPoint(this, -0.5, 0.0);
		inThird = new ConnectionInPoint(this, 0.0, -0.5);
		ins = Stream.of(inFirst, inSecond, inThird).collect(Collectors.toList());
	}

	@Override
	public List<ConnectionInPoint> getInPoints() {
		return packPoints(inFirst, inSecond, inThird);
	}

	@Override
	public List<ConnectionOutPoint> getOutPoints() {
		return packPoints(out);
	}
	
	@Override
	public void connectService(BaseService service) {
		super.connectService(service);
		turnOn();
	}

	public int amountOfConnectedPoints() {
		return ins.stream().map(o -> (o.isFree() ? 0 : 1)).reduce(0, (a, b) -> a + b);

	}

	@Override
	public synchronized Integer getFillCount() {
		// nothing stays inside
		return 0;
	}

}
