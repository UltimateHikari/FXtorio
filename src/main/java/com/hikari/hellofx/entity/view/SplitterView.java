package com.hikari.hellofx.entity.view;

import com.hikari.hellofx.entity.BindingController;

import javafx.scene.paint.Color;

public class SplitterView extends BasicEntityView{
	private static final Color SPLITTERCOLOR = Color.CORAL;

	public SplitterView(double x, double y, BindingController controller) {
		super(x, y, controller, SPLITTERCOLOR);
	}

	public static Color getColor() {
		return SPLITTERCOLOR;
	}
}
