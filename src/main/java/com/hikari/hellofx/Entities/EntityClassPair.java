package com.hikari.hellofx.Entities;

import java.lang.reflect.InvocationTargetException;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityView;

public class EntityClassPair {
	private final Class<? extends BaseModel> model;
	private final Class<? extends BasicEntityView> view;

	public EntityClassPair(Class<? extends BaseModel> model_, Class<? extends BasicEntityView> view_) {
		model = model_;
		view = view_;
		System.out.println("created " + this);
	}

	public BasicEntityView getViewInstance(double x, double y, BindingController controller)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		return view.getDeclaredConstructor(double.class, double.class, BindingController.class)
				.newInstance(x, y, controller);
	}

	public BaseModel getModelInstance() throws Exception {
		return model.getDeclaredConstructor().newInstance();
	}
	
	public Class<? extends BasicEntityView> getViewClass(){
		return view;
	}

	@Override
	public String toString() {
		return model.getName() + "::" + view.getName();
	}
}
