package com.hikari.hellofx.Entities.ClassPairs;

import java.lang.reflect.InvocationTargetException;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.BindingController;
import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityView;

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
