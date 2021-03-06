package com.hikari.hellofx.entity.view;

import com.hikari.hellofx.entity.BindingController;

import javafx.scene.paint.Color;

public class MinerView extends BasicEntityView {
	private static final Color MINERCOLOR = Color.BEIGE;

	public MinerView(double x, double y, BindingController controller) {
		super(x, y, controller, MINERCOLOR);
	}

	public static Color getColor() {
		return MINERCOLOR;
	}
}
