package com.hikari.hellofx.entity.classpair;

import java.lang.reflect.InvocationTargetException;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.view.BasicEntityView;
import com.hikari.hellofx.entity.BindingController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityClassPair implements IClassPair {
	private final Class<? extends BaseModel> model;
	private final Class<? extends BasicEntityView> view;

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
