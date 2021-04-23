package com.hikari.hellofx.Entities.Connectable.Assembler;

import com.hikari.hellofx.Entities.BindingController;
import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityView;

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
