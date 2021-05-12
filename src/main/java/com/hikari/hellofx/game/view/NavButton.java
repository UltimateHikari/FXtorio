package com.hikari.hellofx.game.view;

import com.hikari.hellofx.SceneClass;
import com.hikari.hellofx.SceneController;

import javafx.scene.control.Button;

public class NavButton extends Button{
	public NavButton(SceneController controller, String label, SceneClass scene) {
		super(label);
		super.setOnAction((event) -> controller.changeCurrentScene(scene));
	}
}
