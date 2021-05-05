package com.hikari.hellofx.Entities.ClassPairs;

import java.lang.reflect.InvocationTargetException;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.BindingController;
import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityView;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class EntityClassPair implements IClassPair {
	private final Class<? extends BaseModel> model;
	private final Class<? extends BasicEntityView> view;

	public EntityClassPair(Class<? extends BaseModel> model_, Class<? extends BasicEntityView> view_) {
		model = model_;
		view = view_;
		log.info("created " + this);
	}

	public BasicEntityView getViewInstance(double x, double y, BindingController controller)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		return view.getDeclaredConstructor(double.class, double.class, BindingController.class).newInstance(x, y,
				controller);
	}

	public BaseModel getModelInstance() throws Exception {
		return model.getDeclaredConstructor().newInstance();
	}

	@Override
	public String toString() {
		return model.getName() + "::" + view.getName();
	}
}
