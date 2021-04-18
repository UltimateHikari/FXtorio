package com.hikari.hellofx.Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.hikari.hellofx.Base.BaseModel;

public class ConstructorModel extends BaseModel implements IConnectable, IPowerConnectable, ISuspendable{
	private boolean isTurnedOn = false;
	private int productionTime = 1000;
	private ConnectableState state = ConnectableState.NO_POINTS;
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
	private final ConnectionOutPoint out = new ConnectionOutPoint(this, 0.5, 0.0);
	private final Object monitor = new Object();
	@Override
	public void turnOff() {
		isTurnedOn = false;
		super.notifySubs();
	}

	@Override
	public void turnOn() {
		isTurnedOn = true;
		synchronized(monitor) {
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
		return packPoints(in);
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
		while (true) {
			System.out.println("started");
			try {
				if(isTurnedOn == false) {
					synchronized(monitor) {
						monitor.wait();
					}
				}
				Object o = in.get();
				sleep(productionTime);
				out.put(o);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
