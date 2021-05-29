package com.hikari.hellofx.entity.model.belt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.IConnection;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;

import javafx.geometry.Point2D;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Belt extends BaseModel implements IConnection, ISuspendable {
	private static final double CELL_SIZE = 20;
	private boolean isDetached = false;
	private static final int CELL_TRAVEL_TIME = 500;

	private int slotsCount;

	// 0 .. slotsCount - 1
	private List<ItemCarriage> items;
	@Getter
	private ConnectionInPoint dst;
	@Getter
	private ConnectionOutPoint src;

	public Belt(ConnectionOutPoint source, ConnectionInPoint destination) {
		connectDestination(destination);
		destination.connect(this);
		connectSource(source);
		source.connect(this);
		initDistance();
		initItems();
		notifySubs(); // for constructing & subbing carts
	}

	private void initDistance() {
		var start = new Point2D(src.getLastViewX(), src.getLastViewY());
		var distance = start.distance(new Point2D(dst.getLastViewX(), dst.getLastViewY()));
		slotsCount = (int) Math.ceil(distance / CELL_SIZE);
		log.info("Creating belt of " + slotsCount + " size;");
	}

	private void initItems() {
		items = Stream.generate(() -> new ItemCarriage(slotsCount - 1)).limit(slotsCount)
				.collect(Collectors.toList());
	}

	@Override
	public void turnOff() {
		//always on
	}

	@Override
	public void turnOn() {
		//always on
	}

	@Override
	public boolean isOn() {
		return true;
	}

	@Override
	public void connectDestination(ConnectionInPoint o) {
		dst = o;
	}

	public void connectSource(ConnectionOutPoint o) {
		src = o;
		o.connect(this);
	}

	public long getCellTravelTime() {
		return CELL_TRAVEL_TIME;
	}

	public double getSlotsCount() {
		return slotsCount;
	}

	public List<ItemCarriage> getItemModels() {
		return items;
	}

	@Override
	public void markDetached() {
		//TODO disconnect from other end 
		isDetached = true;
	}

	@Override
	public boolean isDetached() {
		return isDetached;
	}

}
