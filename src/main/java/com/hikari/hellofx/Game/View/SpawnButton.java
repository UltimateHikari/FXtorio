package com.hikari.hellofx.Game.View;

import com.hikari.hellofx.Entities.EntityClassPair;
import com.hikari.hellofx.Game.GameAction;
import com.hikari.hellofx.Game.GameController;

import javafx.scene.control.Button;

public class SpawnButton extends Button{
	public SpawnButton(GameController gcontroller, String label, EntityClassPair entityClassPair){
		super(label);
		setOnMouseClicked((event) -> gcontroller.act(event, GameAction.ENTER_SPAWN, entityClassPair));
	}
}
