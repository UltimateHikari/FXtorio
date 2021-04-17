package com.hikari.hellofx.Views.GameScene;

import com.hikari.hellofx.GameAction;
import com.hikari.hellofx.GameController;

import javafx.scene.control.Button;

public class SpawnButton extends Button{
	public SpawnButton(GameController gcontroller, String label, String entityClassName){
		super(label);
		setOnMouseClicked((event) -> gcontroller.act(event, GameAction.ENTER_SPAWN, entityClassName));
	}
}
