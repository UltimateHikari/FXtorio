package com.hikari.hellofx.Views;

import com.hikari.hellofx.BindingController;

import javafx.scene.paint.Color;

public class StorageView extends BasicEntityView {
	private static final Color STORAGECOLOR = Color.AQUAMARINE;
	public StorageView(double x, double y, BindingController controller) {
		super(x,y,controller, STORAGECOLOR);
	}
}
