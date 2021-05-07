package com.hikari.hellofx.game.view;

import com.hikari.hellofx.SceneController;

import javafx.scene.control.Button;

public class NavButton extends Button{
	public NavButton(SceneController controller, String label, String scene) {
		super(label);
		super.setOnAction((event) -> controller.changeCurrentScene(scene));
	}
}
