package com.hikari.hellofx.Views;

import com.hikari.hellofx.BindingController;
import javafx.scene.paint.Color;


public class MinerView extends BasicEntityView {
	private static final Color MINERCOLOR = Color.BEIGE;
	public MinerView(double x, double y, BindingController controller) {
		super(x,y,controller, MINERCOLOR);
	}
}
