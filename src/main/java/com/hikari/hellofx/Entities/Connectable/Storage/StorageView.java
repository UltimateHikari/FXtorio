package com.hikari.hellofx.Entities.Connectable.Storage;

import com.hikari.hellofx.Entities.BindingController;
import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityView;

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
