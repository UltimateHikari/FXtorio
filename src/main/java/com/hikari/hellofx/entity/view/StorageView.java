package com.hikari.hellofx.entity.view;

import com.hikari.hellofx.entity.BindingController;

import javafx.scene.paint.Color;

public class StorageView extends BasicEntityView {
	private static final Color STORAGECOLOR = Color.AQUAMARINE;

	public StorageView(double x, double y, BindingController controller) {
		super(x, y, controller, STORAGECOLOR);
	}

	public static Color getColor() {
		return STORAGECOLOR;
	}
}
