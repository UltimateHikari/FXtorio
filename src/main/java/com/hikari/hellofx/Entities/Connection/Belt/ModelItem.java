package com.hikari.hellofx.Entities.Connection.Belt;

import com.hikari.hellofx.Base.BaseModel;

public class ModelItem extends BaseModel{
	private Object payload;
	private int position = 0;
	private final int maxPosition;
	//IDLE is not really used..
	private ModelItemStatus status = ModelItemStatus.IDLE;
	
	public ModelItem(Object o_, int maxPosition_) {
		payload = o_;
		maxPosition = maxPosition_;
	}
	
	public void move() {
		position++;
		System.out.println("moved cell with " + payload + " to " + position);
		status = ModelItemStatus.MOVED;
		notifySubs();
	}
	
	public void dispatch() {
		position = 0;
		status = ModelItemStatus.DISPATCHED;
		notifySubs();
	}
	
	public boolean notEndReached() {
		return (position != maxPosition);
	}
	
	public boolean notClosePredecessorTo(ModelItem item) {
		return position < item.getPosition() - 1;
	}

	
	public int getPosition() {
		return position;
	}

	public ModelItemStatus getStatus() {
		return status;
	}
	
	public void putItem(Object o) {
		payload = o;
	}
	
	public Object removeItem() {
		Object result = payload;
		payload = null;
		return result;
	}
		
	public String getPayloadName() {
		return payload != null ? payload.toString() : "ERR";
	}
}
