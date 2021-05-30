package com.hikari.hellofx.entity.model.belt;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.Item;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class ItemCarriage extends BaseModel{
	
	private Item payload;
	private int position = 0;
	private final int maxPosition;
	//IDLE is not really used..
	private ModelItemStatus status = ModelItemStatus.IDLE;
	
	public void move() {
		position++;
		log.trace("moved cell with " + payload + " to " + position);
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
	
	public boolean notClosePredecessorTo(ItemCarriage item) {
		return position < item.getPosition() - 1;
	}

	
	private Integer getPosition() {
		return position;
	}

	public ModelItemStatus getStatus() {
		return status;
	}
	
	public void putItem(Item o) {
		payload = o;
	}
	
	public Item removeItem() {
		Item result = payload;
		payload = null;
		return result;
	}
	
	public Item peekItem() {
		return payload;
	}
		
	public String getPayloadName() {
		return payload != null ? payload.toString() : "ERR";
	}
	
	@Override
	public String toString() {
		return getPosition().toString();
	}
}
