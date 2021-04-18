package com.hikari.hellofx.Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.hikari.hellofx.Base.BaseModel;

public abstract class BasicEntityModel extends BaseModel implements IConnectable, IPowerConnectable, ISuspendable {
	private boolean isTurnedOn = false;
	private ConnectableState state = ConnectableState.NO_POINTS;
	private final Object monitor = new Object();

	@Override
	public void turnOff() {
		isTurnedOn = false;
		super.notifySubs();
	}

	@Override
	public void turnOn() {
		isTurnedOn = true;
		synchronized (monitor) {
			monitor.notify();
		}
		super.notifySubs();
	}

	public boolean isOn() {
		return isTurnedOn;
	}

	@Override
	public boolean hasFreePoints() {
		// TODO Auto-generated method stub
		return false;
	}

	public void despawn() {
		// TODO
	}

	@Override
	public ConnectableState getConnectableState() {
		return state;
	}

	@Override
	public void setConnectableState(ConnectableState state_) {
		state = state_;
		notifySubs();
	}

	protected <T> ArrayList<T> packPoints(T... args) {
		return new ArrayList<T>(Arrays.asList(args).stream().filter(w -> (((ConnectionPoint) w).isFree() == true))
				.collect(Collectors.toList()));
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				if(!isOn()) {
					synchronized(monitor) {
						monitor.wait();
					}
				}
				performCycle();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected abstract void performCycle() throws InterruptedException;
}
