package com.hikari.hellofx.game.view;

import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.game.GameAction;

import javafx.scene.control.Button;

public class SuspendButton extends Button{
	public SuspendButton(BindingController bController, String label) {
		super(label);
		setOnMouseClicked((event)->bController.handleClick(event, GameAction.SUSPEND));
	}
}
