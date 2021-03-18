package com.hikari.hellofx;

import javafx.scene.control.Button;

public class DespawnButton extends Button{
	public DespawnButton(BindingController bController, String label) {
		super(label);
		setOnMouseClicked((event)->bController.handleClick(event, GameAction.DESPAWN));
	}
}
