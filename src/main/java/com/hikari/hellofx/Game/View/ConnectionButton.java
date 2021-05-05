package com.hikari.hellofx.Game.View;

import com.hikari.hellofx.Entities.ClassPairs.ConnectionClassPair;
import com.hikari.hellofx.Game.GameAction;
import com.hikari.hellofx.Game.GameController;

import javafx.scene.control.Button;

public class ConnectionButton extends Button{
	public ConnectionButton(GameController gcontroller, String label,
			ConnectionClassPair classPair){
		super(label);
		setOnMouseClicked((event) -> gcontroller.act(event, GameAction.ENTER_CONNECT, classPair));
	}
}
