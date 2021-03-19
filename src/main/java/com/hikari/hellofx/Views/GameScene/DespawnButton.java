package com.hikari.hellofx.Views.GameScene;

import com.hikari.hellofx.BindingController;
import com.hikari.hellofx.GameAction;

import javafx.scene.control.Button;

public class DespawnButton extends Button{
	public DespawnButton(BindingController bController, String label) {
		super(label);
		setOnMouseClicked((event)->bController.handleClick(event, GameAction.DESPAWN));
	}
}
