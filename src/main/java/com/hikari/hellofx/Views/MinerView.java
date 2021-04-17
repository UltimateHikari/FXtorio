package com.hikari.hellofx.Views;

import com.hikari.hellofx.BindingController;
import javafx.scene.paint.Color;


public class MinerView extends BasicEntityView {
	private static final Color constructorColor = Color.GREENYELLOW;
	public MinerView(Double x, Double y, BindingController controller) {
		super(x,y,controller, constructorColor);
	}
}
