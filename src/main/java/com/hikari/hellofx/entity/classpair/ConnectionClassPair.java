package com.hikari.hellofx.entity.classpair;

import java.lang.reflect.InvocationTargetException;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;
import com.hikari.hellofx.entity.view.BasicConnectionView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConnectionClassPair implements IClassPair {
	private final Class<? extends BaseModel> model;
	private final Class<? extends BasicConnectionView> view;

	public BasicConnectionView getViewInstance(ConnectionOutPoint out, ConnectionInPoint in)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		return view.getDeclaredConstructor(ConnectionOutPoint.class, ConnectionInPoint.class).newInstance(out, in);
	}

	public BaseModel getModelInstance(ConnectionOutPoint out, ConnectionInPoint in)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		return model.getDeclaredConstructor(ConnectionOutPoint.class, ConnectionInPoint.class).newInstance(out, in);
	}

	public Class<? extends BasicConnectionView> getViewClass() {
		return view;
	}

	@Override
	public String toString() {
		return model.getName() + "::" + view.getName();
	}
}
