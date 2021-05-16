package com.hikari.hellofx.entity.view.info;

import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.game.GameAction;

import javafx.scene.control.Button;

public class DespawnButton extends Button{
	public DespawnButton(BindingController bController, String label) {
		super(label);
		setOnMouseClicked((event)->bController.handleClick(event, GameAction.DESPAWN));
	}
}