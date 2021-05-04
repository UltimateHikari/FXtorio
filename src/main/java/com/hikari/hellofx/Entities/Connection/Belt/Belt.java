package com.hikari.hellofx.Entities.Connection.Belt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.ILoggable;
import com.hikari.hellofx.Entities.Connectable.ISuspendable;
import com.hikari.hellofx.Entities.Connection.ConnectionEvent;
import com.hikari.hellofx.Entities.Connection.IConnection;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;
import javafx.geometry.Point2D;

public class Belt extends BaseModel implements IConnection, ISuspendable, ILoggable{
	private static final double CELL_SIZE = 20;
	private static final int CELL_TRAVEL_TIME = 50;
	
	private double distance;
	private int slotsCount;
	private int travelTime;
	
	//0 .. slotsCount - 1
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
		Point2D start = new Point2D(src.getLastViewX(), src.getLastViewY());
		distance = start.distance(
				new Point2D(
						dst.getLastViewX(),
						dst.getLastViewY()
						));
		slotsCount = (int) Math.ceil(distance/CELL_SIZE);
		travelTime = CELL_TRAVEL_TIME*slotsCount;
		log("Creating belt of " + slotsCount + " size;");
	}
	
	private void initItems() {
		items = Stream
				.generate(() -> new ModelItem(null, slotsCount - 1))
				.collect(Collectors.toList());
	}
	
	private void moveCells() {
		for(int i = base; i < end; i++) {
			ModelItem current = items.get(i);
			if(i == base && current.notEndReached()) {
				current.move();
			} else if(current.notEndReached() && current.notClosePredecessorTo(items.get(i - 1))){
				current.move();
			}
		}
	}
	
	private int cycleIncrement(int a) {
		return a + 1 % slotsCount;
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

	@Override //TODO remove
	public ConnectionEvent getLastConnectionEvent() {
		return null;
	}

	@Override
	public void connectDestination(ConnectionInPoint o) {
		dst = o;
	}
	
	public void connectSource(ConnectionOutPoint o) {
		src = o;
	}
	
	private boolean haveReadyItems() {
		return (base != end && !items.get(base).notEndReached());
	}
	
	private boolean haveEmptySlots() {
		// aiming for 1 item less than full capacity
		// because base == full - indistinguishable, full or empty
		return base != cycleIncrement(end);
	}

	public void run() {
		while(true) {
			try {
				offerItem();
				moveCells();
				sleep(travelTime);
				pollItem();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void offerItem() {
		if(haveReadyItems()) {
			ModelItem current = items.get(base);
			Object o = current.removeItem();
			if(dst.offer(o)) {
				base = cycleIncrement(base);
				current.dispatch();;
			} else {
				current.putItem(o);
			}
		}
	}
	
	private void pollItem() {
		if(haveEmptySlots()) {
			Object o = src.poll();
			if(o != null) {
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
	
	public List<ModelItem> getItemModels(){
		return items;
	}
	
}
