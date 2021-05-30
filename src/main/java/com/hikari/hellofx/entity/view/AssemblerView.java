package com.hikari.hellofx.entity.view;

import com.hikari.hellofx.entity.BindingController;

import javafx.scene.paint.Color;

public class AssemblerView extends BasicEntityView {
	private static final Color ASSEMBLERCOLOR = Color.GRAY;

	public AssemblerView(double x, double y, BindingController controller) {
		super(x, y, controller, ASSEMBLERCOLOR);
	}

	public static Color getColor() {
		return ASSEMBLERCOLOR;
	}
}
