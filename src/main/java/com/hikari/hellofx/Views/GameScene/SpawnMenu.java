package com.hikari.hellofx.Views.GameScene;

import com.hikari.hellofx.GameController;
import com.hikari.hellofx.SceneController;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SpawnMenu extends HBox {
	public SpawnMenu(SceneController controller, GameController gcontroller) {
		add(new Text("spawn menu here"));
		setSpacing(10);
		add(new Text("and here"));
		add(new SpawnButton(gcontroller, "SampleSpawn"));
		add(new NavButton(controller, "Main menu", "MenuScene"));
	}
	/*
	 * Syntax sugar
	 */
	public void add(Node child){
		getChildren().add(child);
	}
}
