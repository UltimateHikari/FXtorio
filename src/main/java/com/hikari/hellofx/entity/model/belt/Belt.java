package com.hikari.hellofx.entity.model.belt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hikari.hellofx.entity.model.basic.BasicConnectionModel;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;

import javafx.geometry.Point2D;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Belt extends BasicConnectionModel {
	private static final double CELL_SIZE = 20;
	private static final int CELL_TRAVEL_TIME = 500;

	@Getter
	private int slotsCount;

	// 0 .. slotsCount - 1
	private List<ItemCarriage> items;
	@Getter
	private ConnectionInPoint dst;
	@Getter
	private ConnectionOutPoint src;

	public Belt(ConnectionOutPoint source, ConnectionInPoint destination) {
		connectDestination(destination);
		connectSource(source);
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
		items = Stream.generate(() -> new ItemCarriage(slotsCount - 1))
				.limit(slotsCount).collect(Collectors.toList());
	}

	@Override
	public void connectDestination(ConnectionInPoint o) {
		dst = o;
		o.connect(this);
	}

	public void connectSource(ConnectionOutPoint o) {
		src = o;
		o.connect(this);
	}

	public long getCellTravelTime() {
		return CELL_TRAVEL_TIME;
	}

	public List<ItemCarriage> getItemModels() {
		return items;
	}

}
