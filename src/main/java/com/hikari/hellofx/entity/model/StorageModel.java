package com.hikari.hellofx.entity.model;

import java.util.ArrayDeque;
import java.util.List;

import com.hikari.hellofx.entity.model.basic.BasicEntityModel;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;

import lombok.Getter;

import java.util.Collections;

public class StorageModel extends BasicEntityModel{
	private static final int STORAGE_SIZE = 100;
	@Getter
	private final ArrayDeque<Object> storage = new ArrayDeque<Object>(STORAGE_SIZE);
	@Getter
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
	
	@Override
	public List<ConnectionInPoint> getInPoints() {
		return packPoints(in);
	}

	@Override
	public List<ConnectionOutPoint> getOutPoints() {
		return Collections.emptyList();
	}

	@Override
	public Integer getFillCount() {
		return storage.size();
	}
}
