package com.hikari.hellofx.entity.model;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.IPowerConnectable;
import com.hikari.hellofx.entity.IProducer;
import com.hikari.hellofx.entity.IServiceNotifier;
import com.hikari.hellofx.entity.Items;
import com.hikari.hellofx.entity.Recipe;
import com.hikari.hellofx.entity.RecipeManager;

import lombok.Getter;

public abstract class BasicEntityModel extends BaseModel implements IConnectable, IPowerConnectable, IServiceNotifier, IProducer{
	private Object payload = null;
	private boolean isTurnedOn = false;
	private BaseService basicService = null;
	@Getter
	private Recipe currentRecipe;
	private ConnectableState state = ConnectableState.NO_POINTS;
	
	@Override
	public void setCurrentRecipe(Items item) throws NoSuchElementException{
		List<Recipe> list =  RecipeManager.instance().getRecipeList(this.getClass());
		currentRecipe = list.stream().filter(r -> r.produce() == item).findFirst().orElseThrow();
	}

	@Override
	public void turnOff() {
		isTurnedOn = false;
		super.notifySubs();
	}

	public void notifyService() {
		synchronized (basicService) {
			basicService.notify();
		}
	}

	@Override
	public void turnOn() {
		isTurnedOn = true;
		notifyService();
		super.notifySubs();
	}

	public boolean isOn() {
		return isTurnedOn;
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
	
	public synchronized void setPayload(Object o) {
		payload = o; 
	}
	
	@Override
	public synchronized Integer getFillCount() {
		return payload == null ? 0 : 1;
	}

	@SafeVarargs
	protected final <T extends ConnectionPoint> List<T> packPoints(T... args) {
		return Arrays.asList(args).stream().filter(w -> (w.isFree()))
				.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public void connectService(BaseService service) {
		basicService = service;
	}

	@Override
	public void disconnectService() {
		basicService = null;
	}
	
}
