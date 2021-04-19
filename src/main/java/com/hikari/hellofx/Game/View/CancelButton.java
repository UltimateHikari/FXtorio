package com.hikari.hellofx.Game.View;

import com.hikari.hellofx.Game.GameAction;
import com.hikari.hellofx.Game.GameController;

import javafx.scene.control.Button;

public class CancelButton extends Button{
	public CancelButton(GameController gcontroller, String label){
		super(label);
		setOnMouseClicked((event) -> gcontroller.act(event, GameAction.CANCEL));
	}
}
