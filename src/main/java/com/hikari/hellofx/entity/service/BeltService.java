package com.hikari.hellofx.entity.service;

import java.util.stream.Collectors;

import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.belt.Belt;
import com.hikari.hellofx.entity.model.belt.ItemCarriage;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BeltService extends BasicConnectionService {
	private int base = 0;
	private int end = 0;
	private Belt bModel;

	public BeltService(ISuspendable model) {
		super(model);
		bModel = (Belt) model;
	}

	private boolean haveReadyItems() {
		return (base != end && !bModel.getItemModels().get(base).notEndReached());
	}

	private boolean haveEmptySlots() {
		// aiming for 1 item less than full capacity
		// because base == full - indistinguishable, full or empty
		return base != cycleIncrement(end);
	}

	protected void performCycle() throws InterruptedException {
		offerItem();
		moveCells();
		sleep(bModel.getCellTravelTime());
		pollItem();
	}

	private void offerItem() {
		if (haveReadyItems()) {
			ItemCarriage current = bModel.getItemModels().get(base);
			var o = current.removeItem();
			if (bModel.getDst().offer(o)) {
				base = cycleIncrement(base);
				current.dispatch();
			} else {
				current.putItem(o);
			}
		}
	}

	private void pollItem() {
		if (haveEmptySlots()) {
			Item o = bModel.getSrc().poll();
			if (o != null) {
				bModel.getItemModels().get(end).putItem(o);
				end = cycleIncrement(end);
			}
		}
	}

	private void moveCells() {
		log.debug("base/end: " + base + "/" + end);
		for (int i = base; i != end; i = cycleIncrement(i)) {
			ItemCarriage current = bModel.getItemModels().get(i);
			if ((i == base && current.notEndReached()) || (current.notEndReached()
					&& current.notClosePredecessorTo(bModel.getItemModels().get(cycleDecrement(i))))) {
				current.move();
			}
		}
		log.debug(bModel.getItemModels().stream().map(ItemCarriage::toString).collect(Collectors.joining(",")));
	}

	private int cycleDecrement(int a) {
		return Math.floorMod(a - 1, bModel.getSlotsCount());
	}

	private int cycleIncrement(int a) {
		return (a + 1) % bModel.getSlotsCount();
	}

}
