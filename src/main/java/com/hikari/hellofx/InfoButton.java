package com.hikari.hellofx;

import javafx.scene.control.Button;

public class InfoButton extends Button{
	public InfoButton(BindingController bController, String label) {
		super(label);
		setOnMouseClicked((event)->bController.handleClick(event, GameAction.SUSPEND));
	}
}
