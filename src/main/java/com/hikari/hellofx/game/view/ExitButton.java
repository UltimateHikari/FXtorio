package com.hikari.hellofx.game.view;

import com.hikari.hellofx.SceneController;

import javafx.scene.control.Button;

public class ExitButton extends Button{
	public ExitButton(SceneController controller, String label) {
		super(label);
		super.setOnAction(event -> controller.exit());
	}
}
