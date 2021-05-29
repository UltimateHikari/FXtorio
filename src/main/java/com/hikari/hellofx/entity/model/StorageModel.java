package com.hikari.hellofx.entity.model;

import java.util.List;
import java.util.Map;

import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.basic.BasicEntityModel;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;
import java.util.HashMap;

@Log4j2
public class StorageModel extends BasicEntityModel {
	@Getter
	private final Map<String, Integer> storage = new HashMap<>();
	@Getter
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
	@Getter
	private Integer storageSize = 0;

	@Override
	public List<ConnectionInPoint> getInPoints() {
		return packPoints(in);
	}

	@Override
	public List<ConnectionOutPoint> getOutPoints() {
		return Collections.emptyList();
	}

	@Override
	public synchronized Integer getFillCount() {
		return storageSize;
	}

	public void addItem(Item o) {
		storage.merge(o.toString(), 1, ((a,b) -> a + b));
		storageSize++;
		log.info(storage);
	}
}
