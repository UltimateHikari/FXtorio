package com.hikari.hellofx.Views;

import com.hikari.hellofx.BindingController;
import javafx.scene.paint.Color;


public class ConstructorView extends BasicEntityView {
	private static final Color constructorColor = Color.GREENYELLOW;
	public ConstructorView(Double x, Double y, BindingController controller) {
		super(x,y,controller, constructorColor);
	}
}
