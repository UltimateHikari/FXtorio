package com.hikari.hellofx;

import javafx.scene.control.Button;

public class SuspendButton extends Button{
	public SuspendButton(BindingController bController, String label) {
		super(label);
		setOnMouseClicked((event)->bController.handleClick(event, GameAction.SUSPEND));
	}
}
