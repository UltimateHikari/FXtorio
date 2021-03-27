package com.hikari.hellofx.Views.GameScene;

import com.hikari.hellofx.GameAction;
import com.hikari.hellofx.GameController;

import javafx.scene.control.Button;

public class ConnectionButton extends Button{
	public ConnectionButton(GameController gcontroller, String label){
		super(label);
		setOnMouseClicked((event) -> gcontroller.act(event, GameAction.ENTER_CONNECT));
	}
}
