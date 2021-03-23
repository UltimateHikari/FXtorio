package com.hikari.hellofx.Entities;

import java.util.ArrayList;
import java.util.Arrays;

import com.hikari.hellofx.Base.BaseModel;

public class ConstructorModel extends BaseModel implements IConnectable, IPowerConnectable, ISuspendable{
	private boolean isTurnedOn = false;
	private ConnectableState state = ConnectableState.NO_POINTS;
	private final ConnectionInPoint in = new ConnectionInPoint(this, -0.5, 0.0);
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

	public boolean getState() {
		return isTurnedOn;
	}
	
	@Override
	public boolean hasFreePoints() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connect(IConnection connection, ConnectionPoint point) {
		// TODO Auto-generated method stub
		
	}

	public void despawn() {
		System.out.println("goodbye");
	}

	@Override
	public ConnectableState getConnectableState() {
		return state;
	}

	@Override
	public void setConnectableState(ConnectableState state_) {
		state = state_;
	}

	@Override
	public ArrayList<ConnectionInPoint> getInPoints() {
		return new ArrayList<ConnectionInPoint>(Arrays.asList(in));
	}

	@Override
	public ArrayList<ConnectionOutPoint> getOutPoints() {
		return new ArrayList<ConnectionOutPoint>(Arrays.asList(out));

	}

}
