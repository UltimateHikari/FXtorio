package com.hikari.hellofx.Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.hikari.hellofx.Base.BaseModel;

public class MinerModel extends BaseModel implements IConnectable, IPowerConnectable, ISuspendable{
	private boolean isTurnedOn = false;
	private int productionTime = 1000;
	private ConnectableState state = ConnectableState.NO_POINTS;
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);
	@Override
	public void turnOff() {
		isTurnedOn = false;
		super.notifySubs();
	}

	@Override
	public void turnOn() {
		isTurnedOn = true;
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

	@Override
	public ArrayList<ConnectionInPoint> getInPoints() {
		//TODO think about sending immutable list
		return new ArrayList<ConnectionInPoint>();
	}

	@Override
	public ArrayList<ConnectionOutPoint> getOutPoints() {
		return packPoints(out);
	}
	
	@SafeVarargs
	private <T> ArrayList<T> packPoints(T ... args) {
		return new ArrayList<T>(Arrays.asList(args).stream()
				.filter(w -> (((ConnectionPoint) w).isFree() == true)).collect(Collectors.toList()));
	}
	
	@Override 
	public void run() {
		while(true) {
			Object object = new Object();
			try {
				sleep(productionTime);
				out.put(object);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
