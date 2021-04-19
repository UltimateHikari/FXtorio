package com.hikari.hellofx.Game.View;

import com.hikari.hellofx.Entities.BindingController;
import com.hikari.hellofx.Game.GameAction;

import javafx.scene.control.Button;

public class DespawnButton extends Button{
	public DespawnButton(BindingController bController, String label) {
		super(label);
		setOnMouseClicked((event)->bController.handleClick(event, GameAction.DESPAWN));
	}
}
