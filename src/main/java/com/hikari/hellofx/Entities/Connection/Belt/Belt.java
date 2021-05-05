package com.hikari.hellofx.Entities.Connection.Belt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.Connectable.ISuspendable;
import com.hikari.hellofx.Entities.Connection.IConnection;
import com.hikari.hellofx.Entities.Connection.Conveyor.ConnectionEvent;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;
import javafx.geometry.Point2D;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Belt extends BaseModel implements IConnection, ISuspendable {
	private static final double CELL_SIZE = 20;
	private static final int CELL_TRAVEL_TIME = 500;

	private int slotsCount;

	// 0 .. slotsCount - 1
	private List<ModelItem> items;
	private int base;
	private int end;
	private ConnectionInPoint dst;
	private ConnectionOutPoint src;
	private boolean isTurnedOn = false;

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
		items = Stream.generate(() -> new ModelItem(slotsCount - 1)).limit(slotsCount)
				.collect(Collectors.toList());
	}

	private void moveCells() {
		log.debug("base/end: " + base + "/" + end);
		for (int i = base; i != end; i = cycleIncrement(i)) {
			ModelItem current = items.get(i);
			if ((i == base && current.notEndReached())
					|| (current.notEndReached() && current.notClosePredecessorTo(items.get(cycleDecrement(i))))) {
				current.move();
			}
		}
		log.debug(items.stream().map((i) -> ((Integer) i.getPosition()).toString()).collect(Collectors.joining(",")));
	}

	private int cycleDecrement(int a) {
		return Math.floorMod(a - 1, slotsCount);
	}

	private int cycleIncrement(int a) {
		return (a + 1) % slotsCount;
	}

	@Override
	public void turnOff() {
		isTurnedOn = false;
	}

	@Override
	public void turnOn() {
		isTurnedOn = true;
	}

	@Override
	public boolean isOn() {
		return isTurnedOn;
	}

	@Override // TODO remove
	public ConnectionEvent getLastConnectionEvent() {
		return null;
	}

	@Override
	public void connectDestination(ConnectionInPoint o) {
		dst = o;
	}

	public void connectSource(ConnectionOutPoint o) {
		src = o;
		o.connect(this);
	}

	private boolean haveReadyItems() {
		return (base != end && !items.get(base).notEndReached());
	}

	private boolean haveEmptySlots() {
		// aiming for 1 item less than full capacity
		// because base == full - indistinguishable, full or empty
		return base != cycleIncrement(end);
	}

	@Override
	public void run() {
		while (true) {
			try {
				offerItem();
				moveCells();
				sleep(CELL_TRAVEL_TIME);
				pollItem();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void offerItem() {
		if (haveReadyItems()) {
			ModelItem current = items.get(base);
			Object o = current.removeItem();
			if (dst.offer(o)) {
				base = cycleIncrement(base);
				current.dispatch();
				;
			} else {
				current.putItem(o);
			}
		}
	}

	private void pollItem() {
		if (haveEmptySlots()) {
			Object o = src.poll();
			if (o != null) {
				items.get(end).putItem(o);
				end = cycleIncrement(end);
			}
		}
	}

	public long getCellTravelTime() {
		return CELL_TRAVEL_TIME;
	}

	public double getSlotsCount() {
		return slotsCount;
	}

	public List<ModelItem> getItemModels() {
		return items;
	}

}
