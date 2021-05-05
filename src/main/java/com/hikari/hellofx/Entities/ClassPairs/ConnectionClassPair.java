package com.hikari.hellofx.Entities.ClassPairs;

import java.lang.reflect.InvocationTargetException;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.Connection.BasicConnectionView;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConnectionClassPair implements IClassPair {
	private final Class<? extends BaseModel> model;
	private final Class<? extends BasicConnectionView> view;

	public ConnectionClassPair(Class<? extends BaseModel> model_, Class<? extends BasicConnectionView> view_) {
		model = model_;
		view = view_;
		log.info("created " + this);
	}

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
