package com.hikari.hellofx.entity.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hikari.hellofx.base.BaseService;

import lombok.Getter;

public class SplitterModel extends BasicEntityModel {
	@Getter
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
	private final ConnectionOutPoint outFirst;
	private final ConnectionOutPoint outSecond;
	private final ConnectionOutPoint outThird;
	@Getter
	private final List<ConnectionOutPoint> outs;

	public SplitterModel() {
		outFirst = new ConnectionOutPoint(this, 0.0, 0.5);
		outSecond = new ConnectionOutPoint(this, 0.5, 0.0);
		outThird = new ConnectionOutPoint(this, 0.0, -0.5);
		outs = Stream.of(outFirst, outSecond, outThird).collect(Collectors.toList());
	}

	@Override
	public List<ConnectionInPoint> getInPoints() {
		return packPoints(in);
	}

	@Override
	public List<ConnectionOutPoint> getOutPoints() {
		return packPoints(outFirst, outSecond, outThird);
	}
	
	@Override
	public void connectService(BaseService service) {
		super.connectService(service);
		turnOn();
	}

	public int amountOfConnectedPoints() {
		return outs.stream().map((o) -> (o.isFree() ? 0 : 1)).reduce(0, (a, b) -> a + b);

	}

	@Override
	public synchronized Integer getFillCount() {
		// nothing stays inside
		return 0;
	}

}
