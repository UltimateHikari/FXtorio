package com.hikari.hellofx.Views;

import com.hikari.hellofx.BindingController;
import javafx.scene.paint.Color;


public class ConstructorView extends BasicEntityView {
	private static final Color CONSTRUCTORCOLOR = Color.GREENYELLOW;
	public ConstructorView(double x, double y, BindingController controller) {
		super(x,y,controller, CONSTRUCTORCOLOR);
	}
}
