package com.hikari.hellofx.entity.view;

import com.hikari.hellofx.entity.BindingController;

import javafx.scene.paint.Color;

public class MergerView extends BasicEntityView{
	private static final Color MERGERCOLOR = Color.ALICEBLUE;

	public MergerView(double x, double y, BindingController controller) {
		super(x, y, controller, MERGERCOLOR);
	}

	public static Color getColor() {
		return MERGERCOLOR;
	}
}
