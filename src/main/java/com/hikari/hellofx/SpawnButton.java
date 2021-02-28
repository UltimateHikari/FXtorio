package com.hikari.hellofx;

import javafx.scene.control.Button;

public class SpawnButton extends Button{
	public SpawnButton(GameController gcontroller, String label){
		super(label);
		setOnMouseClicked((event) -> gcontroller.enableSpawningState());
	}
}
