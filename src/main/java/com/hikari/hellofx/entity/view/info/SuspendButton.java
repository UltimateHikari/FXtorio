package com.hikari.hellofx.entity.view.info;

import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.game.GameAction;
import com.hikari.hellofx.game.control.BareAction;

import javafx.scene.control.Button;

public class SuspendButton extends Button {
	public SuspendButton(BindingController bController, String label) {
		super(label);
		setOnMouseClicked(event -> bController.handleClick(new BareAction(GameAction.SUSPEND, event)));
	}
}
