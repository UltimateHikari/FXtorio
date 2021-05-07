package com.hikari.hellofx.game.view;

import com.hikari.hellofx.game.GameAction;
import com.hikari.hellofx.game.GameController;
import com.hikari.hellofx.game.classpack.ConnectionClassPack;

import javafx.scene.control.Button;

public class ConnectionButton extends Button{
	public ConnectionButton(GameController gcontroller, String label,
			ConnectionClassPack classPack){
		super(label);
		setOnMouseClicked((event) -> gcontroller.act(event, GameAction.ENTER_CONNECT, classPack));
	}
}
