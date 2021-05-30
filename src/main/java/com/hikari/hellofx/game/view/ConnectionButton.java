package com.hikari.hellofx.game.view;

import com.hikari.hellofx.game.GameAction;
import com.hikari.hellofx.game.GameController;
import com.hikari.hellofx.game.control.ConnectionClassPack;
import com.hikari.hellofx.game.control.PackAction;

import javafx.scene.control.Button;

public class ConnectionButton extends Button {
	public ConnectionButton(GameController gcontroller, String label, ConnectionClassPack classPack) {
		super(label);
		setOnMouseClicked(event -> gcontroller.act(new PackAction(GameAction.ENTER_CONNECT, event, classPack)));
	}
}
