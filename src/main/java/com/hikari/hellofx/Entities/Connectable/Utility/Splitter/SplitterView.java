package com.hikari.hellofx.Entities.Connectable.Utility.Splitter;

import com.hikari.hellofx.Entities.BindingController;
import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityView;

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
