package com.hikari.hellofx.game.view;

import com.hikari.hellofx.entity.classpair.ConnectionClassPair;
import com.hikari.hellofx.game.GameAction;
import com.hikari.hellofx.game.GameController;

import javafx.scene.control.Button;

public class ConnectionButton extends Button{
	public ConnectionButton(GameController gcontroller, String label,
			ConnectionClassPair classPair){
		super(label);
		setOnMouseClicked((event) -> gcontroller.act(event, GameAction.ENTER_CONNECT, classPair));
	}
}
