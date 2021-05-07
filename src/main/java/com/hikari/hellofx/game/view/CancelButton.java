package com.hikari.hellofx.game.view;

import com.hikari.hellofx.game.GameAction;
import com.hikari.hellofx.game.GameController;

import javafx.scene.control.Button;

public class CancelButton extends Button{
	public CancelButton(GameController gcontroller, String label){
		super(label);
		setOnMouseClicked((event) -> gcontroller.act(event, GameAction.CANCEL));
	}
}
