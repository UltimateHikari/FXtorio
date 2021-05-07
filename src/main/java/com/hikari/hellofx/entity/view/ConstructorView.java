package com.hikari.hellofx.entity.view;

import com.hikari.hellofx.entity.BindingController;

import javafx.scene.paint.Color;

public class ConstructorView extends BasicEntityView {
	private static final Color CONSTRUCTORCOLOR = Color.GREENYELLOW;

	public ConstructorView(double x, double y, BindingController controller) {
		super(x, y, controller, CONSTRUCTORCOLOR);
	}

	public static Color getColor() {
		return CONSTRUCTORCOLOR;
	}
}
