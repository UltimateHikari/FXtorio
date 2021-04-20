package com.hikari.hellofx.Entities.Connectable.Constructor;

import com.hikari.hellofx.Entities.BindingController;
import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityView;

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
