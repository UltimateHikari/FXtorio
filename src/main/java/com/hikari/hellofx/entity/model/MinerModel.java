package com.hikari.hellofx.entity.model;

import java.util.Collections;
import java.util.List;

import com.hikari.hellofx.entity.model.basic.BasicProducerModel;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;

import lombok.Getter;

public class MinerModel extends BasicProducerModel{
	@Getter
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);
	
	@Override
	public List<ConnectionInPoint> getInPoints() {
		return Collections.emptyList();
	}

	@Override
	public List<ConnectionOutPoint> getOutPoints() {
		return packPoints(out);
	}
		
}
